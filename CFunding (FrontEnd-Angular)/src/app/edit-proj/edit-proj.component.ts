import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {Projet} from '../../models/Projet';
import {ProjetService} from '../../Services/projet.service';
import {Contrepartie} from '../../models/Contrepartie';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-proj',
  templateUrl: './edit-proj.component.html',
  styleUrls: ['./edit-proj.component.scss']
})
export class EditProjComponent implements OnInit {

  isLinear = false;
  list: Array<FormGroup> = [];
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  myControl = new FormControl();
  public userfile: any = File;
  mescontri  = new Contrepartie(0," "," "," "," "," ")
  srcimg: any = '';
  options: string[] = ['Agriculture', 'Alimentation', 'Art & Photo', 'Artisanat', 'Film & Video', 'Jeux',
    'Journalisme', 'Livres', 'Mode & Design', 'Musique', 'Santé & Education', 'Sport', 'Technologie', 'Theatre & dance'];
  filteredOptions: Observable<string[]>;
  public imagePath;
  thirdFormGroup: FormGroup;
  projet : Projet = new Projet('ddddd');
  title : any;


  constructor(private formBuilder: FormBuilder, private projectService: ProjetService , private route: ActivatedRoute , private router : Router) {
    this.title = this.route.snapshot.params['id'];
    console.log(this.title)
    this.projet.titre = this.title
    this.projectService.oneProjet(this.projet).subscribe(d => {



      this.projet = d.body as unknown as Projet
      console.log(this.projet);
      this.mescontri = this.projet.contreparties[0]
    }, error => {
      console.log(error);

    });

  }

  ngOnInit() {
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
    console.log(this.projet)
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
    console.log(this.thirdFormGroup.value);
    const formData = new FormData();
    console.log(this.userfile);

    formData.append('projet', JSON.stringify(this.projet));
    formData.append('file', this.userfile);
    const contrepartie = new Contrepartie(this.mescontri.montant, this.mescontri.titre,
      this.mescontri.description, this.mescontri.livraison, this.title ,
      this.mescontri.id);
    this.projectService.saveProject(formData).subscribe(data => {
      this.projectService.saveContrepartie(contrepartie).subscribe(d => {
        console.log(data);
        console.log(d);
        this.router.navigateByUrl("/mesprojects");


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

