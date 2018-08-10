import { Injectable} from '@angular/core';
import { HttpClient , HttpHeaders, HttpParams} from '@angular/common/http';
import { AppContext } from '../models/app-context.model';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { CalculateCommission } from '../models/calculate-commission.model';
import { environment } from '../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable()
export class CalculateCommissionService {
    public appContext: Object;
    
    constructor(private http: HttpClient) {}
     
    getAppContext(): Observable<AppContext>{
       return this.http.get<AppContext>(environment.getAppContextURL);            
    }
    
    calculateCommission(calculateCommission: CalculateCommission): Observable<String[]>{  
          
        return this.http.post<CalculateCommission>(environment.calculateCommissionURL,
        JSON.stringify(calculateCommission), httpOptions). pipe(tap((agentCommissionResult: String[])=>{
               //console.log(agentCommissionResult)
        }))
    }

}