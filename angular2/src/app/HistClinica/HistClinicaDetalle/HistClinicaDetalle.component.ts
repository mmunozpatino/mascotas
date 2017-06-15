import{Component, Input, OnInit} from '@angular/core';
import {HistoriaClinica, HistClinicaService} from '../HistClinica.service';
import { ActivatedRoute, Router }   from '@angular/router';
import {HistClinica} from '../HistClinica.component';


@Component({
    selector: 'HistClinicaDetalle',
    templateUrl: 'HistClinicaDetalle.component.html',
    styleUrls:['HistClinicaDetalle.component.css']
})
export class HistClinicaDetalle implements OnInit{
    historia: HistoriaClinica;
    id: string;
    HC : HistClinica;
        
    constructor(
        private service: HistClinicaService,
        private route: ActivatedRoute,
        private router: Router
    ){}
    ngOnInit(){
        this.route.params.subscribe(params => {
            this.id = params['id'];
            if (this.id){
                this.service.getById(this.id).then(hist => this.historia = hist);
                //this.historia = this.service.buscarHistoria(id);
                //this.service.buscarHistoria(id).then(hist => this.historia = hist);
            }
        })
    }
    goBack(){
        this.router.navigate(['mascotas']);
    }   
}