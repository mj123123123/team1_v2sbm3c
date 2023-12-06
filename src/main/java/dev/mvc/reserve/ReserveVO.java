package dev.mvc.reserve;

/*CREATE TABLE Reserve (
    Reserveno INT PRIMARY KEY,
    routesno INT,
    seatno INT,
    customerno INT,
    reserve_time TIMESTAMP,
);*/

public class ReserveVO {
  
  private int reserveno;
  private int routesno;
  private int seatno;
  private int customerno;
  private String name;
  
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getReserveno() {
    return reserveno;
  }
  public void setReserveno(int reserveno) {
    this.reserveno = reserveno;
  }
  public int getRoutesno() {
    return routesno;
  }
  public void setRoutesno(int routesno) {
    this.routesno = routesno;
  }
  public int getSeatno() {
    return seatno;
  }
  public void setSeatno(int seatno) {
    this.seatno = seatno;
  }
  public int getCustomerno() {
    return customerno;
  }
  public void setCustomerno(int customerno) {
    this.customerno = customerno;
  }
  
  @Override
  public String toString() {
    return "ReserveVO [reserveno=" + reserveno + ", routesno=" + routesno + ", seatno=" + seatno + ", customerno="
        + customerno + ", name=" + name + "]";
  }
  

  }

  
  
