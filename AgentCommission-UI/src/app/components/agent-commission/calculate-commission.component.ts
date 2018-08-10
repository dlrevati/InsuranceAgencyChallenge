import { Component, OnInit, Input} from '@angular/core';
import { CalculateCommission } from '../../models/calculate-commission.model'; 
import { CalculateCommissionService} from  '../../services/calculate-commission.service';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { AppContext } from '../../models/app-context.model';
import { CommissionPlan, PlanDescription } from '../../models/commission-plan.model';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title">Result</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
        <div *ngIf="agentsList">
        <p class="form-group" *ngFor="let agent of agentsList" >
                {{agent}}
        </p> 
        </div>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
    </div>
  `
})
export class NgbdModalContent {
  @Input() agentsList: string[];

  constructor(public activeModal: NgbActiveModal) {}
}
@Component({
  selector: 'calculate-commission',
  templateUrl: './calculate-commission.component.html',
  styleUrls: ['./calculate-commission.component.css']
})
export class CalculateCommissionComponent implements OnInit{
    public policyAmount:number;
    public commissionPlan: string;
    public agentCount:number=1; //default selling agent
    public agent_threshold:number;
    public errorMessage:string;
    public resultErrorMessage: string;
    public agentCommissionResult: String[];
    public agentList:string[];
    public calculateCommission:CalculateCommission;
    public appContext: AppContext;
    public commissionPlanList: CommissionPlan[];
    public agentCommission: string[];
    public planDescription: PlanDescription[]=[];
    public isContextSet: boolean =true;

    constructor(private service:CalculateCommissionService, private http:HttpClient,
        private modalService: NgbModal) {      }
    
    ngOnInit(){
        this.appContext=new AppContext();
        this.getAppContext();        
        //console.log("App Context..");
        //console.log(this.appContext);
        this.agentList=new Array<string>(); 
    }
    getAppContext() {
        this.service.getAppContext().subscribe(context=> {    
            if(context!=null || context !=undefined)  {
                this.isContextSet=true;
                console.log("Success Loading configuration!!!");
                this.appContext.agentsThreshold=context.agentsThreshold;
                this.appContext.commissionPlans=context.commissionPlans;            
                this.appContext.agentCommissions=context.agentCommissions;
                this.appContext.commissionPlansList=context.commissionPlansList; 
                
                this.agent_threshold=this.appContext.agentsThreshold;
                this.commissionPlanList=this.appContext.commissionPlansList;
                this.agentCommission=this.appContext.agentCommissions.split(",");               
                this.agentList[0]="Added "+ this.agentCommission[0] + "% commission";
                //console.log("Agent threshold:", this.agent_threshold);
                //console.log("Plans List:", this.commissionPlanList);  

                for (let index = 0; index < this.commissionPlanList.length; index++) {
                    const element = this.commissionPlanList[index];
                    let plan=new PlanDescription();
                    plan.planName= element.planName;
                    plan.agents=element.planDescription.split(",");
                    this.planDescription[index]=plan;
                }
                //console.log(this.planDescription)
            } else {
                this.isContextSet=false;
                console.log("Error loading configuration !!!");
            }           
        } );        
    }   
    addSuperAgent(){      
        if(this.agentCount>=this.agent_threshold){
            this.errorMessage="Can add upto "+ this.agent_threshold + " agents only!"            
        }else{
            //this.agentList[this.agentCount]="Added Super Agent "+ (this.agentCount)
            this.agentList[this.agentCount]="Added "+ this.agentCommission[this.agentCount] +"% commission"
            this.agentCount++;
            this.errorMessage=""; 
        }
    }
    reduceSuperAgent(){
        if(this.agentCount==1){
            this.errorMessage="Each Policy will always have atleast 1 selling agent."
        }else{
            this.agentCount--;
            this.agentList.length=this.agentCount;
            this.errorMessage="";            
        }
    }
    onSubmit() {
        this.calculateCommission=new CalculateCommission();
        this.calculateCommission.policyAmount=this.policyAmount;
        this.calculateCommission.commissionPlan=this.commissionPlan;
        this.calculateCommission.agentCount=this.agentCount;
        //console.log(this.calculateCommission);
        //validate policy amount with decimals
        let index=this.policyAmount.toString().indexOf(".");
        let validateAmount=false;
        if(index>0){
            let diff= this.policyAmount.toString().length - index -1;
            if(diff<=2){
                validateAmount=true;
            }
        } 
        else{ 
            // for whole numbers
            validateAmount=true;
        }       
       if((this.calculateCommission.policyAmount!=undefined && this.calculateCommission.commissionPlan !=undefined)
            && this.calculateCommission.policyAmount >=1 && validateAmount )
        {
          this.errorMessage="";
          const modalRef = this.modalService.open(NgbdModalContent);
          this.service.calculateCommission(this.calculateCommission as CalculateCommission).subscribe(
           (response)=>{
               this.agentCommissionResult=response;
               modalRef.componentInstance.agentsList = this.agentCommissionResult;
           });
        } 
        else{
            this.resultErrorMessage="Enter proper values in all the fields";
        }    
    }
}