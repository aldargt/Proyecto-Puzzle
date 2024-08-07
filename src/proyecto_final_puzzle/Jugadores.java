
package proyecto_final_puzzle;

public class Jugadores{
    String Nicks;
    int Movidas;
    int Horas;
    int Minutos;
    int Segundos;

    public Jugadores(String nombre, int movidas, int hora, int minutos, int segundos) {
        this.Nicks = nombre;
        this.Movidas = movidas;
        this.Horas = hora;
        this.Minutos = minutos;
        this.Segundos = segundos;
    }
    

    public String getNicks() {
        return Nicks;
    }

    public void setNicks(String Nicks) {
        this.Nicks = Nicks;
    }

    public int getMovidas() {
        return Movidas;
    }

    public void setMovidas(int Movidas) {
        this.Movidas = Movidas;
    }

    public int getHoras() {
        return Horas;
    }

    public void setHoras(int Horas) {
        this.Horas = Horas;
    }

    public int getMinutos() {
        return Minutos;
    }

    public void setMinutos(int Minutos) {
        this.Minutos = Minutos;
    }

    public int getSegundos() {
        return Segundos;
    }

    public void setSegundos(int Segundos) {
        this.Segundos = Segundos;
    }
    
     
    
}