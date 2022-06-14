package assignment3_package;

import java.io.File;

/* Student Name: Kaiyan Chen, Simul Bista, Jaydenn(Ching-Ting) Chang
* Student ID: N01489178, N01489966, N01511476
* Section: ITC-5201-RIA
*/

/*************************************************************************************************
*  ITC-5201-RIA – Assignment 3                                                                                                                                *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy. *

*  No part of this assignment has been copied manually or electronically from any other source *

*  (including web sites) or distributed to other students/social media.                                                       *

*  Name: Kaiyan Chen Student ID: N01489178 Date: 6/14/2022 *
*  Name: Simul Bista Student ID: N01489966 Date: 6/14/2022 *
*  Name: Jaydenn(Ching-Ting) Chang Student ID: N01511476 Date: 6/14/2022 *

* *************************************************************************************************/


import java.io.RandomAccessFile;
public class FileProcessing {
    
	private static File file = new File("src/assignment3_package/product.dat");
	private static RandomAccessFile myFile;
	private static final int ID_SIZE = 3; //3 characters for id field (001)
	private static final int NAME_SIZE = 20; // 20 characters for name field
	private static final int PRICE_SIZE = 7; //5 characters for price field (1000.00)
	private static final int RECORD_SIZE = ID_SIZE + NAME_SIZE + PRICE_SIZE; //size of a record or a line (30 characters)
	private static int id =0;
	private static String paddedName;
	private static String paddedId;
	private static String paddedPrice;
	
	
	
	
	//method to write a product info(name and price, id = auto generated) to the binary file
    public static int addProduct(String name, double price) {
    	//hold the value of the id to return it later
    	int added = -1;
    	//position of the start of each record
    	int position=0;
        	try{
        		
//        		create the binary file
        		myFile = new RandomAccessFile(file, "rw");
        			if(file.exists()) {
        				int byteread;
						char result;
						String s ="";
						while((byteread = myFile.read())!=-1) {
							result = (char)byteread;
							//string s contains all the characters from the file
							s+=result;
						}
						//removing trailing spaces that might be there in case:
						// when you delete a record , the code is written such that there are trailing spaces
						// so removing this trailing no. of spaces
						s= s.replaceFirst("\\s++$", "");
						//set the value of position to the start of the record
						position = (s.length()/RECORD_SIZE) +1;
						
						//increment id for the record
						//always set the id to the last record's id +1, i.e. if last record has id=4, next record should have id 5
						if(s.length()>RECORD_SIZE) {
							id = Integer.parseInt(s.substring(s.length()-RECORD_SIZE,s.length()-RECORD_SIZE +4).trim()) + 1;
						}
						//if there is no record in the file
						else {
							id++;
						}
        			}	
        			
        		//padding the fields to make the character length as fixed
        		paddedName = getPaddedName(name);
        		paddedId = getPaddedId(id);
        		paddedPrice = gePaddedPrice(price);
        		
        		//move the pointer position to the start of the next record
                myFile.seek((position-1)*RECORD_SIZE);
                //write the data in the binary file
                myFile.writeBytes(paddedId);
                myFile.writeBytes(paddedName);
                myFile.writeBytes(paddedPrice);
                added = id;
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        //on successful add, will return the id of the record otherwise will return -1
        return added;
    }

    
  //method to update a product info(by id) to the binary file
    public static boolean updateProduct(int id, String name, double price) {
    	//flag to keep track if the value has been updated or not
    	boolean updated = false;
    	//store the id of a record in the file
    	int searchedId;
    	//padding the fields to make the character length as fixed
    	paddedName = getPaddedName(name);
		paddedId = getPaddedId(id);
		paddedPrice = gePaddedPrice(price);
    	try{
	    		myFile = new RandomAccessFile(file, "rw");
		    	//start of read
				if(file.exists()) {
						int byteread;
						char result;
						String s ="";
						while((byteread = myFile.read())!=-1) {
							result = (char)byteread;
							s+=result;
						}
						//loop through each record pointing to the start of each record
						for(int i=0;i<s.length();i=i+RECORD_SIZE) {
							//get the first 3 characters of the record which will hold the padded id [(example:**3) - * represents space]
							//then trim the the extra spaces to get the exact id and store it in searchedId variable
							searchedId = Integer.parseInt(s.substring(i,i+4).trim());
							//if the user input id matches the id of the record in the file
							if(id==searchedId) {
								//move pointer position to the start of that record where the id matched
								myFile.seek(i);
								//write the updated data
								myFile.writeBytes(paddedId);
				                myFile.writeBytes(paddedName);
				                myFile.writeBytes(paddedPrice);
				                //set the flag to true
				                updated = true;
				                //move out of the loop
				                break;
							}
						}
					}
			}catch(Exception e) {
				e.printStackTrace();
			}
    	//updated=true means update successful otherwise unsuccessful
    	return updated;
    }

    // if deletion is successful, return true
    public static boolean deleteProduct(int id) {
    	//flag to keep track of the delete status
    	boolean deleted = false;
    	//store the id of a record in the file
    	int searchedId;
    	//suppose the 2nd record is to be deleted, then this variable will hold all the records starting from 3rd record onwards
    	//so that the remaining characters can be inserted from record 2 onwards (replacing the original 2nd record which gets deleted/replaced)
    	String remainingRecord="";
    	//padding the id field
		paddedId = getPaddedId(id);
    	try{
	    		myFile = new RandomAccessFile(file, "rw");
		    	//start of read
				if(file.exists()) {
						int byteread;
						char result;
						String s ="";
						while((byteread = myFile.read())!=-1) {
							result = (char)byteread;
							s+=result;
						}
						//loop through each record pointing to the start of each record
						for(int i=0;i<s.length();i=i+RECORD_SIZE) {
							//get the first 3 characters of the record which will hold the padded id [(example:**3) - * represents space]
							//then trim the the extra spaces to get the exact id and store it in searchedId variable
							searchedId = Integer.parseInt(s.substring(i,i+4).trim());
							//if the user input id matches the id of the record in the file
							if(id==searchedId) {
								//move pointer position to the start of that record where the id matched
								myFile.seek(i);
								//if its the last line, we replace the line with spaces to delete the content
								if(i==(s.length()-RECORD_SIZE)) {	
									String clear=" ";
									clear = String.format("%31s", clear);
					                myFile.writeBytes(clear);
								}
								//if its a line other than the last one,
								//the records that is after the record that is to be deleted, is stored in remainingRecord variable along with trailing spaces equivalent to a record size
								//and remainingRecord is written to the file starting from the position of the record to be deleted
								//hence replacing the record to be deleted
								else {
									remainingRecord += s.substring(i+RECORD_SIZE,s.length()-1);
									remainingRecord += String.format("%31s", " ");
					                myFile.writeBytes(remainingRecord);
								}
								//set flag to true
				                deleted = true;
				                //break out of the loop
				                break;
							}
						}
					}
			}catch(Exception e) {
				e.printStackTrace();
			}
    	//return true if delete was successful
    	return deleted;
    }
    
    //padding spaces to the left of the name field to make the total characters 20
    private static String getPaddedName(String n) {
    	while(n.length()<20) {
			n=' ' + n;
		}
    	return n;
    }
    //padding spaces to the right of the id field to make the total characters 3
    private static String getPaddedId(int id) {
    	String paddedId = Integer.toString(id);
    	while(paddedId.length()<3) {
			paddedId= ' ' + paddedId;
		}
    	return paddedId;
    }
    
    //padding spaces to the right of the price field to make the total characters 7  
    private static String gePaddedPrice(double p) {
    	//round to 2 decimal places
    	double roundedPrice = Math.round(p*100.00)/100.00;
		String paddedPrice = Double.toString(roundedPrice);
		while(paddedPrice.length()<7) {
			paddedPrice= ' ' + paddedPrice;
		}
    	return paddedPrice;
    }
}
