import { Component, OnInit, Input } from '@angular/core';
import {HistoriaClinica, HistClinicaService} from '../HistClinica.service';
import {ActivatedRoute,Router} from '@angular/router';

@Component({
    selector: 'HistClincaNueva',
    templateUrl: 'HistClinicaNueva.component.html'
})
export class HistClinicaNueva implements OnInit{
    historia: HistoriaClinica;
    @Input() mascotaId: string;
    errorMessage: string;
    formSubmitted: boolean;
    hist: HistoriaClinica = {id: 9, mascota_id:'2', fecha: '06-06-2017', titulo: 'historia1', descripcion: 'descripcion1', recordatorio: 'recordatorio1'};
  errors: string[] = [];
    constructor(
        private service: HistClinicaService,
        private route: ActivatedRoute,
        private router: Router
    ){}
    ngOnInit(){
        this.historia = {id: null, mascota_id: this.mascotaId, fecha: '', titulo: '', descripcion: '', recordatorio: ''};
    }
    save(){
        /*
        this.historia.id = 6;
        console.log(this.historia);
        this.cleanRestValidations();
        this.historia.mascota_id = this.mascotaId;
        this.service.guardarHistoria(this.historia);
        this.router.navigate(['historia/'+this.mascotaId]);
        this.service.guardarHistoria(this.historia);
        console.log('Se guardo!');
        */
        this.service.guardarHistoria(this.hist).then(historia => this.router.navigate(['historia/'+this.mascotaId])).catch(error => this.procesarValidacionesRest(error));

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