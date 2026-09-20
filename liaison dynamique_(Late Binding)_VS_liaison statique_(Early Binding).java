class Parent {
    // 1. Static
    public static void afficherStatic() {
        System.out.println("Static du Parent");
    }

    // 2. Private
    // (Note : une méthode private n'est pas héritée, mais on peut montrer le masquage/redéfinition illusoire)
    private void afficherPrivate() {
        System.out.println("Private du Parent");
    }
    
    // Méthode intermédiaire pour tester l'appel de la méthode private
    public void testerPrivateDeLInterieur() {
        afficherPrivate();
    }

    // 3. Final
    public final void afficherFinal() {
        System.out.println("Final du Parent (impossible a surcharger)");
    }

    // 4. Public / Protected (Normales)
    public void afficherNormal() {
        System.out.println("Public normal du Parent");
    }
}

class Fille extends Parent {
    // 1. Static (C'est un masquage, pas un override)
    public static void afficherStatic() {
        System.out.println("Static de la Fille");
    }

    // 2. Private (Ce n'est PAS un override, c'est juste une nouvelle méthode privée propre à Fille)
    private void afficherPrivate() {
        System.out.println("Private de la Fille");
    }

    // 3. Final -> Impossible de l'écrire, le compilateur refuserait (Cannot override the final method)

    // 4. Public normal (Vrai Override / Liaison dynamique)
    @Override
    public void afficherNormal() {
        System.out.println("Public normal de la Fille");
    }
}

public class TestLiaison {
    public static void main(String[] args) {
        // Le type de la référence est Parent, mais l'objet réel est Fille
        Parent obj = new Fille();

        System.out.println("--- Cas 1 : STATIC ---");
        obj.afficherStatic(); 
        // Résultat : "Static du Parent" 
        // Raison : Suit le type de la référence (Parent).

        System.out.println("\n--- Cas 2 : PRIVATE ---");
        obj.testerPrivateDeLInterieur(); 
        // Résultat : "Private du Parent"
        // Raison : La méthode private est liée statiquement à la classe où elle est appelée (Parent).

        System.out.println("\n--- Cas 3 : FINAL ---");
        obj.afficherFinal(); 
        // Résultat : "Final du Parent"
        // Raison : Étant final, le compilateur fixe l'appel sur le type de la référence.

        System.out.println("\n--- Cas 4 : PUBLIC NORMAL ---");
        obj.afficherNormal(); 
        // Résultat : "Public normal de la Fille"
        // Raison : C'est la seule qui utilise la liaison dynamique (late binding) et suit le type de l'objet (Fille).
    }
}





/*
1. For public / protected methods (Without @Override)
If you have a normal public method and you write it in the child class without @Override, it is still a true method override (dynamic binding). 
The compiler sees a method with the exact same signature in the parent and child, so it links them together, and the child's version will still execute when you use new Fille().
*/
