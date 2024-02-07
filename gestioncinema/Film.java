public class Film {
    String nom;
    int duree;
    String genre;
    String version;

    public Film(String nom, int duree, String genre, String version) {
        this.nom = nom;
        this.duree = duree;
        this.genre = genre;
        this.version = version;
    }

    public String toString() {
        return "Film [nom=" + nom + ", duree=" + duree + ", genre=" + genre + ", version=" + version + "]";
    }

    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
    }

    public void setDuree(int nouvelleDuree) {
        this.duree = nouvelleDuree;
    }

    public void setGenre(String nouveauGenre) {
        this.genre = nouveauGenre;
    }

    public void setVersion(String nouvelleVersion) {
        this.version = nouvelleVersion;
    }

    public String getNom() {
        return this.nom;
    }
}
