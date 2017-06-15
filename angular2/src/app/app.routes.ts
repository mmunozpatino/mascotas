
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { WelcomeComponent } from './welcome/welcome.component';
import { NuevaMascotaComponent } from './mascota/nueva-mascota.component';
import { MascotaComponent } from './mascota/mascota.component';
import { PerfilComponent } from './perfil/perfil.component';
import { RegistrarUsuarioComponent } from './usuario/registrar-usuario.component';
import {HistClinica} from './HistClinica/HistClinica.component';
import {HistClinicaDetalle} from './HistClinica/HistClinicaDetalle/HistClinicaDetalle.component'
import {HistClinicaNueva} from './HistClinica/HistClinicaNueva/HistClinicaNueva.component';

// Route Configuration
export const routes: Routes = [
    { path: '', component: WelcomeComponent },
    { path: 'perfilUsuario', component: PerfilComponent },
    { path: 'registrarUsuario', component: RegistrarUsuarioComponent },
    { path: 'mascotas', component: MascotaComponent },
    { path: 'nuevaMascota/:id', component: NuevaMascotaComponent },
    { path: 'nuevaMascota', component: NuevaMascotaComponent },
    { path: 'historia/:id', component: HistClinica},
    { path: 'historiaDetalle/:id', component: HistClinicaDetalle},
    { path: 'historiaNueva/:id', component: HistClinicaNueva}
];

export const routing: ModuleWithProviders = RouterModule.forRoot( routes );