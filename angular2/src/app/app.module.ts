import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { APP_BASE_HREF } from '@angular/common';

import { routing } from './app.routes';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { UsuarioService } from './usuario/usuario.service';
import { ProvinciaService } from './provincia/provincia.service';
import { PerfilService } from './perfil/perfil.service';
import { PerfilComponent } from './perfil/perfil.component';
import { MascotaComponent } from './mascota/mascota.component';
import { MenuComponent } from './menu/menu.component';
import { NuevaMascotaComponent } from './mascota/nueva-mascota.component';
import { MascotaService } from './mascota/mascota.service';
import {MascotaDetalle} from './mascota/mascotaDetalle.component';
import { environment } from '../environments/environment';
import { DatePickerPipe } from './tools/common-pipes.pipe';
import { RegistrarUsuarioComponent } from './usuario/registrar-usuario.component';
import { DatePickerModule } from 'ng2-datepicker';
//Funcionalidad de las Historias Clinicas
import {HistClinica} from './HistClinica/HistClinica.component';
import {HistClinicaService} from './HistClinica/HistClinica.service';
import {HistClinicaDetalle} from './HistClinica/HistClinicaDetalle/HistClinicaDetalle.component';
import {HistClinicaNueva} from './HistClinica/HistClinicaNueva/HistClinicaNueva.component'
import { EspeciesService } from "app/especies/especies.sevice";

@NgModule({
  declarations: [
    AppComponent,
    DatePickerPipe,
    WelcomeComponent,
    PerfilComponent,
    MascotaComponent,
    MenuComponent,
    NuevaMascotaComponent,
    MascotaDetalle,
    RegistrarUsuarioComponent,
    //Funcionalidad de las Historias Clinicas
    HistClinica,
    HistClinicaDetalle,
    HistClinicaNueva
  ],
  imports: [
    BrowserModule,
    FormsModule, ReactiveFormsModule,
    HttpModule,
    DatePickerModule,
    routing
  ],
  providers: [
    MascotaService, 
    UsuarioService, 
    ProvinciaService,
    PerfilService, 
    { provide: APP_BASE_HREF, useValue: environment.baseHref },
    //Funcionalidad de las Historias Clinicas
    HistClinicaService,
    EspeciesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
