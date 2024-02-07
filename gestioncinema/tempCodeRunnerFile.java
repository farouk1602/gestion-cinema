private double demanderPrixBillet() {
        String prixStr = JOptionPane.showInputDialog("Prix du billet : ");
        return Double.parseDouble(prixStr);
    }