package es.florida.AEV5_AD;


public class Llibre {
	private Integer identificador;
    private String titol;
    private String autor;
    private String anyNaixement;
    private String anyPublicacio;
    private String editorial;
    private String nombrePagines;

    public Llibre() {};
    
    public Llibre(String tit,String aut,String anyN,String anyP,String edi,String nom){
        this.titol=tit;
        this.autor=aut;
        this.anyNaixement=anyN;
        this.anyPublicacio=anyP;
        this.editorial=edi;
        this.nombrePagines=nom;
    }

    /**
     * Mètode que mostra el títol, l'autor i l'any de publicació d'un llibre
     * */
    public void mostrarInfo() {
    	System.out.println("Titol: " + titol);
    	System.out.println("Autor: " + autor);
    	System.out.println("Any de publicació: " + anyPublicacio);
    }
    
	public Integer getId(){
        return identificador;
    }
    
    public String getTitol(){
        return titol;
    }

    public String getAutor(){
        return autor;
    }
    
    public String getAnyNaixement() {
    	return anyNaixement;
    }

    public String getAnyPublicacio(){
        return anyPublicacio;
    }

    public String getEditorial(){
        return editorial;
    }

    public String getNombrePagines(){
        return nombrePagines;
    }

    public void setId(Integer id){
        identificador=id;
    }
    
    public void setTitol(String tit){
        titol = tit;
    }

    public void setAutor(String aut){
        autor=aut;
    }

    public void setAnyNaixement(String any) {
    	anyNaixement = any;
    }
    
    public void setAnyPublicacio(String any){
        anyPublicacio=any;
    }

    public void setEditorial(String edi){
        editorial=edi;
    }

    public void setNombrePagines(String nom){
        nombrePagines=nom;
    }

}
