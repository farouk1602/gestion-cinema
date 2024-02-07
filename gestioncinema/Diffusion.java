public class Diffusion {
    Film film;
    SalleCinema salleCinema;
    String date;
    String heureDebut;
    String heureFin;

    private int placesDisponibles;

    public Diffusion(Film film, SalleCinema salleCinema, String date, String heureDebut, String heureFin) {
        this.film = film;
        this.salleCinema = salleCinema;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.placesDisponibles = salleCinema.getCapacite();
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void decrementerPlacesDisponibles() {
        if (placesDisponibles > 0) {
            placesDisponibles--;
        }
    }

    public void incrementerPlacesDisponibles() {
        if (placesDisponibles < salleCinema.getCapacite()) {
            placesDisponibles++;
        }
    }

    public String toString() {
        return "Diffusion [film=" + film.nom + ", salleCinema=" + salleCinema.numero + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + "]";
    }

    public void setHeureDebut(String nouvelleHeureDebut) {
        this.heureDebut = nouvelleHeureDebut;
    }

    public void setHeureFin(String nouvelleHeureFin) {
        this.heureFin = nouvelleHeureFin;
    }



    public Film getFilm() {
        return film;
    }
  
    public SalleCinema getSalleCinema() {
        return salleCinema;
    }
    
    public String getDate() {
        return date;
    }

    

    /*     public Object getSalle() {
        return null;
    } */
    /*     public Diffusion getProjection() {
        return this; // Retourne l'instance courante de Diffusion
    } */

     
}
