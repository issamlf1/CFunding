import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {Projet} from '../../models/Projet';
import {ProjetService} from '../../Services/projet.service';
import {Contrepartie} from '../../models/Contrepartie';
import {AuthService} from '../../Services/auth.service';
import {User} from '../../models/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ajout-projet',
  templateUrl: './ajout-projet.component.html',
  styleUrls: ['./ajout-projet.component.scss']
})
export class AjoutProjetComponent implements OnInit {
  isLinear = false;
  list: Array<FormGroup> = [];
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  myControl = new FormControl();
  public userfile: any = File;
  user: User;
  srcimg: any = '';
  options: string[] = ['Agriculture', 'Alimentation', 'Art & Photo', 'Artisanat', 'Film & Video', 'Jeux',
    'Journalisme', 'Livres', 'Mode & Design', 'Musique', 'Santé & Education', 'Sport', 'Technologie', 'Theatre & dance'];
  filteredOptions: Observable<string[]>;
  public imagePath;
  thirdFormGroup: FormGroup;


    constructor(private formBuilder: FormBuilder, private projectService: ProjetService, private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.user = this.authService.giveUser();
    console.log(this.user);
    this.firstFormGroup = this.formBuilder.group({
      titre: ['', Validators.required],
      type: ['', Validators.required],
      categorie: ['', Validators.required],
      description: ['', Validators.required]

    });
    this.secondFormGroup = this.formBuilder.group({
      montant: ['', Validators.required],
      duree: ['', Validators.required]
    });
    this.thirdFormGroup = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      montant: [0, Validators.required],
      livraison: ['', Validators.required]
    });
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }


  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }


  commit() {
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
    console.log(this.thirdFormGroup.value);
    const formData = new FormData();
    console.log(this.userfile);
    const projet = new Projet(this.firstFormGroup.value.titre, this.firstFormGroup.value.description, this.secondFormGroup.value.montant,
      0, this.firstFormGroup.value.type, this.firstFormGroup.value.categorie, false, this.secondFormGroup.value.duree, new Date(), this.user.idUser);
    console.log(this.firstFormGroup.value.type);
    formData.append('projet', JSON.stringify(projet));
    formData.append('file', this.userfile);
    const contrepartie = new Contrepartie(this.thirdFormGroup.value.montant, this.thirdFormGroup.value.titre,
      this.thirdFormGroup.value.description, this.thirdFormGroup.value.livraison, this.firstFormGroup.value.titre);
    this.projectService.saveProject(formData).subscribe(data => {
      this.projectService.saveContrepartie(contrepartie).subscribe(d => {
        console.log(data);
        console.log(d);
        this.router.navigate(['/mesprojects']);

      }, error => {
        console.log(error);

      });

    }, error => {
      console.log(error);

    });

  }

  onSelectFile(event) {
    const file = event.target.files[0];
    console.log(file);
    this.userfile = file;
    const reader = new FileReader();
    this.imagePath = event.target.files;
    reader.readAsDataURL(file);
    reader.onload = (_event) => {
      this.srcimg = reader.result;
      console.log(this.srcimg);
    };
  }

  onCheck() {
    console.log(this.thirdFormGroup.value);
    this.list.push(this.thirdFormGroup);
    this.list.push(this.firstFormGroup);
    this.list.push(this.secondFormGroup);
    for(var i = 0; i < this.list.length; i++)
    {
      console.log(this.list[i]);
    }
  }
}
