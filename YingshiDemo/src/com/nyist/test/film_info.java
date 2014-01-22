package com.nyist.test;

public class film_info
{
   private String name;
   private String brief;
   private String price;
   private String time;
   public film_info()
   {
	   super();
   }
   public film_info(String name,String brief,String time,String price)
   {
	   this.name=name;
	   this.brief=brief;
       this.price=price;
	   this.time=time;
   }
   public void setname(String name)
   {
	   this.name=name;
	   
   }
   public String getname()
   {
	   return name;
   }
   public void setbrief(String brief)
   {
	   this.brief=brief;
   }
   public String getbrief()
   {
	   return brief;
   }
   public void setprice(String price)
   {
	   this.price=price;
   }
   
   public String getprice()
   {
	   return price;
   }
   public void settime(String time)
   {
	   this.time=time;
   }
   public String gettime()
   {
	   return time;
   }
}
