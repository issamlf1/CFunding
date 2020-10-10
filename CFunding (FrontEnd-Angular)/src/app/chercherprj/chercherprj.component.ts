import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {ProjetService} from '../../Services/projet.service';
import {Projet} from '../../models/Projet';
import {Router} from '@angular/router';

@Component({
  selector: 'app-chercherprj',
  templateUrl: './chercherprj.component.html',
  styleUrls: ['./chercherprj.component.scss']
})
export class ChercherprjComponent implements OnInit {
  firstFormGroup: FormGroup;
  myControl = new FormControl();
  options: string[] = ['Agriculture', 'Alimentation', 'Art & Photo', 'Artisanat', 'Film & Video', 'Jeux',
    'Journalisme', 'Livres', 'Mode & Design', 'Musique', 'Santé & Education', 'Sport', 'Technologie', 'Theatre & dance'];
  filteredOptions: Observable<string[]>;
  secondFormGroup: FormGroup;
  etat = false;
  listeproj : Projet[] ;
  constructor(private formBuilder: FormBuilder, private serivceProjet: ProjetService, private router: Router) { }

  ngOnInit() {
    this.firstFormGroup = this.formBuilder.group({
      chercher: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
      categorie: ['', Validators.required]
    });
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );

  }

  onChecher() {
    for (let e of this.options) {
      if (e === this.secondFormGroup.value.categorie) {
        this.serivceProjet.getfilterTwo(this.firstFormGroup.value.chercher, this.etat, this.secondFormGroup.value.categorie).subscribe(
          data => {
            this.listeproj = data as Projet[];
            console.log(this.listeproj);
          }, error => {
            console.log(error);
          }
        );
        break ;
      }
    }
    if ('' === this.secondFormGroup.value.categorie) {
      this.serivceProjet.getfilterOne(this.firstFormGroup.value.chercher, this.etat).subscribe(
        data => {
          this.listeproj = data as Projet[];
          console.log(this.listeproj);
        }, error => {
          console.log(error);
        }
      );
    }
  }
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }

  onSubmit(f: NgForm) {

  }

  getProjetId(titre: string) {
    let url = "detail/"+titre;
    this.router.navigate([url]);
  }

  checkValue(event: any){
    console.log(event);
    this.onChecher();
 }
}
