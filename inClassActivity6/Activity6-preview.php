<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title> Forms </title>
        
    </head>
    <?php
		 $background = "";
         if(empty($_POST['theme'])==false)
         {
            $background =$_POST['theme'];
         }
    ?>
    <body style="font-style:italic; background-color:<?php echo $background; ?>;">
        <p>
            <?php
                if(empty($_POST['name'])==false)
                {
                    echo $_POST['name'];
                }
                else 
                {
                    echo " Name: Not Provided";
                }
            ?>
        </p>
        <p>
            <?php
                if(empty($_POST['uname'])==false)
                {
                    echo $_POST['uname'];
                }
                else 
                {
                    echo "Username: Not Provided";
                }
            ?>
        </p>
        <p>
            <?php
                    
                if(empty($_POST['phoneNumber'])==false)
                {
                    if($_POST['phoneNumber'][0]!="5")
                    {
                        echo "Your phone number must start with a 5";
                    }
                    else if(strlen($_POST['phoneNumber'])!=10)
                    {
                        echo "Your phone number must consist of 10-digits";
                    }
                    else 
                    {
                        $phoneDigits = true;
                        for($i=0; $i<strlen($_POST['phoneNumber']);$i++)
                        {
                            if(!(is_numeric($_POST['phoneNumber'][$i]))){
                                $phoneDigits =false;
                                break;
                            }
                        }
                        if($phoneDigits)
                        {
                            echo $_POST['phoneNumber'];
                        }
                        else
                        {
                            echo "Your phone number must contains only digits.";
                        }
                    }
                    

                }
                else 
                {
                    echo "Phone Number: Not Provided";
                }


            ?>
        </p>
        <p>
           <?php 
                if(empty($_POST['pass'])==false)
                {
                    echo $_POST['pass'];
                    
                }else 
                {
                    echo "Password: Not Provided";
                }
            ?>
        </p>
        <p>
            <?php
                if(empty($_POST['Address'])==false)
                {
                    echo $_POST['Address'];
                }
                else 
                {
                    echo "Address: Not Provided";
                }
            ?>
        </p>

        <p>
           
            <?php
               
                if(empty($_POST['Count'])==false)
                {
                    $dashPosition = strpos($_POST['Count'],"-")+1;
                    $CountryName = substr($_POST['Count'],$dashPosition);
                    echo "$CountryName";
  
                }
                else 
                {
                    echo "Country: Not Provided";
                }
            ?>
        </p>

        <p>
           
            <?php
                if(empty($_POST['ZIP'])==false)
                {
                    echo $_POST['ZIP'];
   
                }
                else 
                {
                    echo "ZIP: Not Provided";
                }
            ?>
        </p>
        <p>
           
            <?php
                if(empty($_POST['email'])==false)
                {
                    if(is_bool($isThereA = strpos($_POST['email'],"@")))
                    {
                        echo "Your email must contain the '@' character";
                    }
                    else
                    {
                        $afterA = substr($_POST['email'],$isThereA);
                        $istThereDot = str_contains($afterA,".");
                        if($istThereDot)
                        {
                            echo $_POST['email'];
                        }
                        else
                        {
                            echo "Your email must contain . after the @ sign";
                        }
                    }
                    
                }
                else 
                {
                    echo "Email: Not Provided";
                }
            ?>
        </p>
        
        

        <p>  
           <?php
               echo " Language ";
               if(empty($_POST['Language'])==false)
               {
                   for($i=0;$i<sizeof($_POST['Language']);$i++)
                   {
                        echo $_POST['Language'][$i].", ";
                   } 
                   echo "<br>";
               }
               else 
               {
                echo "Language: Not Provided";
               }
           ?>
       </p>

       <p>  
           <?php
               if(empty($_POST['sex'])==false)
               {
                    echo $_POST['sex'];
               }
               else 
               {
                echo "Sex: Not Provided";
               }
           ?>
       </p> 
            <?php
                if(empty($_POST['About'])==false)
                {
                    echo $_POST['About'];
                }
                else{
                    echo "About: Not Provided";

                }
            ?>
       <p>

       </p>

    </body>
</html>
