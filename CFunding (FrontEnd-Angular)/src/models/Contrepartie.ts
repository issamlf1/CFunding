export class Contrepartie {
  id? : any;
  montant?: number;
    titre?: string;
    description?: string;
   quantite?: number;
   livraison?: any;
  projets?: any;
  constructor(montant: number, titre: string , description: string, livraison?: any, titrep?: string , id? : any) {
    this.montant = montant;
    this.titre = titre;
    this.description = description ;
    this.livraison = livraison;
    this.projets = { 'titre': titrep };
    this.id = id
  }
}
