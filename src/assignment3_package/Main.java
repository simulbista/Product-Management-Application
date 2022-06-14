package assignment3_package;

import javax.swing.JFrame;

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

public class Main {
    public static void main(String[] args) {

        JFrame frame = new ProductMgntApp();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // test use
//        Product product1 = new Product("first", 25.99);
//        System.out.println(product1);
//        Product product2 = new Product("second", 25);
//        System.out.println(product2);

    }
}
