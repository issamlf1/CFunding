
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import { AuthentificationComponent } from './authentification/authentification.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import {AuthService} from '../Services/auth.service';
import {InscriptionService} from '../Services/inscription.service';
import { AjoutProjetComponent } from './ajout-projet/ajout-projet.component';
import {
  MAT_RADIO_DEFAULT_OPTIONS,
  MatAutocompleteModule,
  MatButtonModule, MatDatepickerModule,
  MatInputModule, MatNativeDateModule, MatRadioModule,
  MatSelectModule,
  MatSliderModule, MatSnackBarModule,
  MatStepperModule
} from '@angular/material';
import { ChercherprjComponent } from './chercherprj/chercherprj.component';
import {ProjetService} from '../Services/projet.service';
import { MesprojetsComponent } from './mesprojets/mesprojets.component';
import { MescontributionComponent } from './mescontribution/mescontribution.component';
import { MonprofilComponent } from './monprofil/monprofil.component';
import { EditProjComponent } from './edit-proj/edit-proj.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import {AuthGuard} from '../Services/auth-guard.service';


const appRoutes: Routes = [
  {path: 'acceuil' , component : AcceuilComponent},
  {path: 'signin' , component : AuthentificationComponent},
  {path: 'signup' , component : InscriptionComponent},
  {path: 'create' , canActivate : [AuthGuard] , component : AjoutProjetComponent},
  {path: 'search' , component : ChercherprjComponent},
  {path: 'mesprojects' , component : MesprojetsComponent},
  {path: 'mescontribution' , component : MescontributionComponent},
  {path: 'edit/:id', canActivate : [AuthGuard] , component : EditProjComponent},
  {path: 'profil' , component : MonprofilComponent},
  {path: 'detail/:titre', canActivate : [AuthGuard] , component : ProjectDetailsComponent},
  {path: '', redirectTo: 'acceuil', pathMatch: 'full'}
];
// @ts-ignore
@NgModule({
  declarations: [
    EditProjComponent,
    AppComponent,
    AuthentificationComponent,
    InscriptionComponent,
    AcceuilComponent,
    AjoutProjetComponent,
    ChercherprjComponent,
    MesprojetsComponent,
    MescontributionComponent,
    MonprofilComponent,
    ProjectDetailsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule, FormsModule, ReactiveFormsModule, MatSliderModule, MatStepperModule,
    MatInputModule,
    MatButtonModule,
    MatAutocompleteModule,
    MatSelectModule ,
    MatDatepickerModule,        // <----- import(must)
    MatNativeDateModule,
    MatSnackBarModule,
    MatRadioModule// <----- import for date formating(optional)
  ],
  providers: [AuthService, InscriptionService , AuthGuard , ProjetService,{
    provide: MAT_RADIO_DEFAULT_OPTIONS,
    useValue: { color: 'accent' }
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
