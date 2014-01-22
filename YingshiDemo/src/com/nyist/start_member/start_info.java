package com.nyist.start_member;

public class start_info
{
   private String name;
   private String brief;

   public start_info()
   {
	   super();
   }
   public start_info(String name,String brief)
   {
	   this.name=name;
	   this.brief=brief;

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
   
}
