public class SalleCinema {
    int numero;
    int capacite;

    public SalleCinema(int numero, int capacite) {
        this.numero = numero;
        this.capacite = capacite;
    }

    public String toString() {
        return "SalleCinema [numero=" + numero + ", capacite=" + capacite + "]";
    }

    public void setNumero(int nouveauNumero) {
        this.numero = nouveauNumero;
    }

    public void setCapacite(int nouvelleCapacite) {
        this.capacite = nouvelleCapacite;
    }


    public int getNumero() {
        return this.numero;
    }
    public int getCapacite() {
        return this.capacite;
    }
}
