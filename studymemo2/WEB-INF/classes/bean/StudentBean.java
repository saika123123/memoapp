package bean;

import java.io.Serializable;

public class StudentBean implements Serializable{
  private String date;
  private String name;
  private String content;
  
  public void setdate(String date){
    this.date = date;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setcontent(String content){
    this.content = content;
  }
  public String getdate(){
    return date;
  }
  public String getName(){
    return name;
  }
  public String getcontent(){
    return content;
  }
}

