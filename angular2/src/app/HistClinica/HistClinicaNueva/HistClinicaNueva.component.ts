import { Component, OnInit, Input } from '@angular/core';
import {HistoriaClinica, HistClinicaService} from '../HistClinica.service';
import {ActivatedRoute,Router} from '@angular/router';

@Component({
    selector: 'HistClincaNueva',
    templateUrl: 'HistClinicaNueva.component.html',
    styleUrls:['HistClinicaNueva.component.css']
})
export class HistClinicaNueva implements OnInit{
    historia: HistoriaClinica;
    mascotaId: string;
    errorMessage: string;
    formSubmitted: boolean;
  errors: string[] = [];
    constructor(
        private service: HistClinicaService,
        private route: ActivatedRoute,
        private router: Router,
    ){}
    ngOnInit(){
        this.route.params.subscribe(params => {
          this.mascotaId = params['id'];
        })
        this.historia = {id: this.mascotaId , fecha: '', titulo: '', descripcion: '', recordatorio: ''};
    }
    save(){
        console.log(this.historia.fecha);
        this.service.guardarHistoria(this.historia,parseInt(this.mascotaId)).then().catch(error => this.procesarValidacionesRest(error));
        this.router.navigate(['historia/'+ this.mascotaId]);
    }
    cancel(){
        this.router.navigate(['historia/'+ this.mascotaId]);
    }
    cleanRestValidations() {
    this.errorMessage = undefined;
    this.errors = [];
  }
  procesarValidacionesRest(data: any) {
    if (data.message) {
      for (const error of data.message) {
        this.errors[error.path] = error.message;
      }
    } else {
      this.errorMessage = data.message;
    }
  }
}