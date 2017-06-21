import {Component,OnInit} from '@angular/core';
import {HistClinicaService, HistoriaClinica} from './HistClinica.service';
import {HISTORIAS} from './HistClinica.bd';
import { ActivatedRoute, Router }   from '@angular/router';


@Component({
    selector: 'HistClinica',
    templateUrl: 'HistClinica.component.html',
    styleUrls:['HistClinica.component.css']
})
export class HistClinica implements OnInit{
    fecha: string;
    historias: HistoriaClinica[];
    idMascota: string;
    nueva: boolean;
    historia: HistoriaClinica;
    noHist: boolean;
    deletes: boolean;
    constructor(
        private service: HistClinicaService,
        private route: ActivatedRoute,
        private router: Router
    ){}
    ngOnInit(){  
        this.cargarComponente();        
    }
    cargarComponente(){
        this.route.params.subscribe(params => {
            this.idMascota = params['id'];
            this.service.getByMascota(this.idMascota).then(hist => {
                this.historias = hist;
                if(this.historias.length != 0){
                    this.noHist = false;
                }else{
                    this.noHist = true;
                }})
        })
    }
    goNew(){
        this.router.navigate(['historiaNueva/'+this.idMascota]);
    }
    delete(id: number){
        this.service.borrarHistoria(id).then(()=> this.cargarComponente());        
    }
    deleteProv(){
        this.deletes = true;
    }
    detail(h: HistoriaClinica){
        this.router.navigate(['/historiaDetalle/'+h.id]);
    }
    borrarTodo(){
        this.service.deleteByMascota(+this.idMascota);
    }
    cancelDelete(){
        this.deletes = false;
    }
}