import { Component, OnInit } from '@angular/core';
import { MascotaService, Mascota } from './mascota.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { DatePickerPipe } from '../tools/common-pipes.pipe';
import { DatePickerModule } from 'ng2-datepicker';
import { EspeciesService, Especie } from "app/especies/especies.sevice";


@Component({
  selector: 'app-nueva-mascota',
  templateUrl: './nueva-mascota.component.html',
  styleUrls: ['mascota.component.css']
})
export class NuevaMascotaComponent implements OnInit {
  mascota: Mascota;
  errorMessage: string;
  formSubmitted: boolean;
  errors: string[] = [];
  especies: Especie[];
  especie: number;

  constructor(private mascotasService: MascotaService,
    private route: ActivatedRoute, private router: Router, private especieService: EspeciesService) {
    this.mascota = { id: null, nombre: '', fechaNacimiento: '', descripcion: '', especie: null, raza: '' };
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let id = +params['id'];
      if (id) {
        this.mascotasService.buscarMascota(id)
          .then(mascota => this.mascota = mascota)
          .catch(error => this.errorMessage = <any>error);
      }
    });
    this.especieService.get().then(esp => {this.especies = esp;
      console.log(this.especies)});
  }

  submitForm() {
    this.especieService.getById(this.especie).then( esp => {this.mascota.especie = esp;
      this.mascotasService.guardarMascota(this.mascota)
      .then(mascota => this.router.navigate(['/mascotas']))
      .catch(error => this.procesarValidacionesRest(error));});
    this.cleanRestValidations();
    console.log(this.mascota);    
  }
  
  
  onDelete() {
    this.cleanRestValidations();
    this.mascotasService.eliminarMascota(this.mascota.id)
      .then(any => this.router.navigate(['/mascotas']))
      .catch(error => this.procesarValidacionesRest(error));
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
  getEspecies(){
    this.especieService.get().then(esp => {this.especies = esp;
    console.log(this.especies)});
  }
}
