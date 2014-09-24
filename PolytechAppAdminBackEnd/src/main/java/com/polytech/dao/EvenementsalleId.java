package com.polytech.dao;
// Generated 23 sept. 2014 09:06:02 by Hibernate Tools 4.3.1



/**
 * EvenementsalleId generated by hbm2java
 */
public class EvenementsalleId  implements java.io.Serializable {


     private int evtid;
     private int salleid;

    public EvenementsalleId() {
    }

    public EvenementsalleId(int evtid, int salleid) {
       this.evtid = evtid;
       this.salleid = salleid;
    }
   
    public int getEvtid() {
        return this.evtid;
    }
    
    public void setEvtid(int evtid) {
        this.evtid = evtid;
    }
    public int getSalleid() {
        return this.salleid;
    }
    
    public void setSalleid(int salleid) {
        this.salleid = salleid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EvenementsalleId) ) return false;
		 EvenementsalleId castOther = ( EvenementsalleId ) other; 
         
		 return (this.getEvtid()==castOther.getEvtid())
 && (this.getSalleid()==castOther.getSalleid());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getEvtid();
         result = 37 * result + this.getSalleid();
         return result;
   }   


}


