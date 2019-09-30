import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { IndexComponent } from './components/index/index.component';
import { ErrorComponent } from './components/error/error.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { NotificacionesComponent } from './components/notificaciones/notificaciones.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { ResponsepublicationComponent } from './components/responsepublication/responsepublication.component';

const routes: Routes = [
	{path: '', component: InicioComponent},
	{path: 'inicio', component: InicioComponent},
	{path: 'index', component: IndexComponent},
	{path: 'login', component: LoginComponent},
	{path: 'logout/:sure', component: LoginComponent},
	{path: 'register', component: RegisterComponent},
	{path: 'perfil', component: UserProfileComponent},
	{path: 'ajustes', component: UserEditComponent},
	{path: 'notificaciones', component: NotificacionesComponent},
	{path: 'perfil/:id', component: ProfileComponent},
	{path: 'usuarios/:id', component: UsuariosComponent},
	{path: 'responder/:id', component: ResponsepublicationComponent},
	{path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
