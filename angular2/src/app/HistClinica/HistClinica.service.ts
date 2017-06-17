import {Injectable} from '@angular/core';
import {HISTORIAS} from './HistClinica.bd';
import { Http, Headers, Response } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';
import { Mascota, MascotaService } from "app/mascota/mascota.service";


@Injectable()
export class HistClinicaService extends RestBaseService{
    
    mascota: Mascota;
    /*get(){
        return HISTORIAS;
    }*/
    post(h: HistoriaClinica){
        HISTORIAS.push(h);
    }
    /*buscarHistoria(id: number){
        return HISTORIAS.find(historia => historia.id == id);
    }*/
    
    
    private url = '/rest/historia';

  constructor(private http: Http, private serviceMascota: MascotaService) { super(); }
  get(): Promise<HistoriaClinica[]> {
    return this.http.get(HistClinicaService.serverUrl + this.url, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as HistoriaClinica[];
      })
      .catch(this.handleError);
  }
 
  getById(id: string): Promise<HistoriaClinica>{
    return this.http.get(HistClinicaService.serverUrl + this.url + '/detalle/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as HistoriaClinica;
      })
      .catch(this.handleError);
  }
  getByMascota(id: string): Promise<HistoriaClinica[]> {
    return this.http.get(HistClinicaService.serverUrl + this.url + '/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as HistoriaClinica[];
      })
      .catch(this.handleError);
  }
 
  guardarHistoria(value: HistoriaClinica, idm: number): Promise<HistoriaClinica> {
      return this.http.post(HistClinicaService.serverUrl + this.url + '/' + idm, JSON.stringify(value), this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as HistoriaClinica;
      })
      .catch(this.handleError);
  }
/*
  eliminarHistoria(id: number): Promise<any> {
    if (id) {
      return this.http.delete(HistClinicaService.serverUrl + this.url + '/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return "";
      })
      .catch(this.handleError);
    }
  }*/

}

export interface HistoriaClinica{
    fecha:string;
    titulo: string;
    descripcion: string;
    recordatorio: string;
}