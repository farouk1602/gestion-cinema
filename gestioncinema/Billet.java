public class Billet {
    int numero;
    double prix;
    Diffusion projectionCinema;

    public Billet(int numero, double prix, Diffusion projectionCinema) {
        this.numero = numero;
        this.prix = prix;
        this.projectionCinema = projectionCinema;
    }

    public String toString() {
        return "Billet [numero=" + numero + ", prix=" + prix + ", projectionCinema=" + projectionCinema + "]";
    }

    public Diffusion getProjection() {
        return projectionCinema;
    }
}
