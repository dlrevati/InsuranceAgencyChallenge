import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { CalculateCommissionComponent,NgbdModalContent } from './components/agent-commission/calculate-commission.component'
import { CalculateCommissionService} from './services/calculate-commission.service';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    AppComponent,
    CalculateCommissionComponent,
    NgbdModalContent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot()
  ],
  providers: [CalculateCommissionService
  ],
  entryComponents:[NgbdModalContent],
  bootstrap: [AppComponent,CalculateCommissionComponent]
})
export class AppModule { }
