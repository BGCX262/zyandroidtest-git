package com.nyist.privilege_member;

public class privilege_info
{
   private String name;
   private String brief;

   public privilege_info()
   {
	   super();
   }
   public privilege_info(String name,String brief)
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
