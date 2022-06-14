package assignment3_package;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

public class ProductMgntApp extends JFrame {

    // Jaydenn
    // Frame setting
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;

    // Jaydenn
    // user input variables
    private String productName;
    private double productPrice;
    private int productId;

    // Jaydenn
    // Common Labels
    JLabel idLabel = new JLabel("ID: ", JLabel.RIGHT);
    JLabel nameLabel = new JLabel("Product Name: ", JLabel.RIGHT);
    JLabel priceLabel = new JLabel("Product Price: ", JLabel.RIGHT);



    // Kaiyan


    // AddPanel elements
	private JLabel addNameLabel;
	private JLabel addPriceLabel;
	private JLabel addMessageLabel;
	
	private JTextField addNameField;
	private JTextField addPriceField;
	
	private JButton addButton;
	
	private JPanel addPanel;
	private JPanel addInputPanel;
	private JPanel addOutputPanel;
	private JPanel addButtonPanel;
//	private JPanel addNamePanel;
//	private JPanel addPricePanel;

    // J's note to Kaiyan: make sure to parse user input into correct data type






    // Jaydenn
    // UpdateDeletePanel Elements

    // JPanel
    JPanel lowerBasePanel;
    JPanel lowerLeftPanel;
    JPanel lowerRightPanel;

    // Labels
    JLabel lowerMessageLabel;

    // Textfields
    JTextField lowerIdTextField;
    JTextField lowerNameTextField;
    JTextField lowerPriceTextField;

    // Buttons
    JButton updateButton;
    JButton deleteButton;




    // Jaydenn
    // Constructor
    public ProductMgntApp() {
        // setup the frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Product Management App");

        // put together the Base Panels to the frame
        // set frame to GridLayout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // add panels to frame
        add(createUpperBasePanel());
        add(createLowerBasePanel());
    }


    // Kaiyan
    public JPanel createUpperBasePanel() {
    	
    	//create labels and text fields of input panel
		addNameLabel = new JLabel("Product Name: ",JLabel.RIGHT);
		addPriceLabel = new JLabel("Product Price: ",JLabel.RIGHT);
		
		addNameField = new JTextField();
		addPriceField = new JTextField();
		
//		addNamePanel = new JPanel();
//		addNamePanel.setLayout(new GridLayout(1,2));
//		addNamePanel.add(addNameLabel);
//		addNamePanel.add(addNameField);
//		
//		addPricePanel = new JPanel();
//		addPricePanel.setLayout(new GridLayout(1,2));
//		addPricePanel.add(addPriceLabel);
//		addPricePanel.add(addPriceField);
		
		//create label and button of output panel
		addMessageLabel = new JLabel("Enter all fields to add.", JLabel.CENTER);
		
		addButton = new JButton("Add");
		//set the size of button and put into a panel
		addButton.setPreferredSize(new Dimension(80, 25));
		addButtonPanel = new JPanel();
		addButtonPanel.add(addButton);
		
		//set main panel's border and grid layout
		addPanel = new JPanel();
		addPanel.setBorder(BorderFactory.createTitledBorder("Add Product"));
		addPanel.setPreferredSize(new Dimension(600,20));
		addPanel.setLayout(new GridLayout(1,2));
		
		//add labels and text fields in input panel with grid layout
		addInputPanel = new JPanel();
		addInputPanel.setLayout(new GridLayout(2,2));
//		GridLayout layout = new GridLayout(2,2);
//		layout.setVgap(10);
//		addInputPanel.setLayout(layout);
		addInputPanel.add(addNameLabel);
		addInputPanel.add(addNameField);
		addInputPanel.add(addPriceLabel);
		addInputPanel.add(addPriceField);	
		
//		addInputPanel.setLayout(new GridLayout(2,1));
//		addInputPanel.add(addNamePanel);
//		addInputPanel.add(addPricePanel);
		
		//add labels and text fields in output panel with border layout
		addOutputPanel = new JPanel();
		addOutputPanel.setLayout(new BorderLayout());
		addOutputPanel.add(addMessageLabel, BorderLayout.CENTER);
		addOutputPanel.add(addButtonPanel, BorderLayout.SOUTH);

		//add 2 panels in main panel
		addPanel.add(addInputPanel);
		addPanel.add(addOutputPanel);
		
		//create ActionListener for addButton
		ActionListener a = new ActionListener()

		{
			public void actionPerformed(ActionEvent ae) {
                try
                {
                	//get product name and price
                    productName = addNameField.getText();
    				productPrice = Double.parseDouble(addPriceField.getText());

    				//check the length of name, it must be 3-20 characters
                    if (productName.length() < 3 || productName.length() > 20)
                    {
                        addMessageLabel.setText("Product name must be within 3 to 20 characters");
                    }
                    // check the price, it must be 0-10,000
                    else if (productPrice < 0 || productPrice >= 10000)
                    {
                        addMessageLabel.setText("Product price must be within 0 to 10,000.");
                    }
                    //if name is vaild
                    else
                    {
//        				boolean addresult = true;//test
        				
        				//call addProduct method
        				int addresult = FileProcessing.addProduct(productName, productPrice);		
        				
        				//if it works, show success message and generated id to user
        				if(addresult>0) 
//        					addMessageLabel.setText("Success! Product ID is "+Product.getProductId());
        				addMessageLabel.setText("Success! Product ID is " + addresult);
        				//else show error message
        				else				
        					addMessageLabel.setText("Something is wrong.");
                    }
                }
                catch (Exception ex)
                {
                    addMessageLabel.setText("Please ensure you have valid input for all fields.");
                }
				


			}
		};
		
		//add the listener to buttons
		addButton.addActionListener(a);

        return addPanel;
    }



    // Jaydenn
    public JPanel createLowerBasePanel() {
        // set up base panel
        lowerBasePanel = new JPanel();
        lowerBasePanel.setLayout(new GridLayout(1,2));

        // add panels and title
        lowerBasePanel.add(createLowerLeftPanel());
        lowerBasePanel.add(createLowerRightPanel());

        // add panel border
        lowerBasePanel.setBorder(BorderFactory.createTitledBorder("Update or Delete Product"));

        // return panel to be added to the frame
        return lowerBasePanel;
    }


    // Jaydenn
    // create update/delete left panel
    public JPanel createLowerLeftPanel() {

        // set up left panel
        lowerLeftPanel = new JPanel();
        lowerLeftPanel.setLayout(new GridLayout(3, 2));

        // create element details
        lowerIdTextField = new JTextField(10);
        lowerNameTextField = new JTextField(10);
        lowerPriceTextField = new JTextField(10);

        // add elements to panel
        lowerLeftPanel.add(idLabel);
        lowerLeftPanel.add(lowerIdTextField);
        lowerLeftPanel.add(nameLabel);
        lowerLeftPanel.add(lowerNameTextField);
        lowerLeftPanel.add(priceLabel);
        lowerLeftPanel.add(lowerPriceTextField);

        // return panel (to lower base panel)
        return lowerLeftPanel;
    }


    // Jaydenn
    // create update/delete right panel
    public JPanel createLowerRightPanel() {

        // set up right panel
        lowerRightPanel = new JPanel();
        lowerRightPanel.setLayout(new BorderLayout());

        // create element details
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        lowerMessageLabel = new JLabel("Enter all fields to update. Enter ID to delete.", JLabel.HORIZONTAL);

        // add ActionListeners
        class updateButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                // parse inputs into correct data type
                try
                {
                    // when parse successfully,
                    productId = Integer.parseInt(lowerIdTextField.getText());
                    productPrice = Double.parseDouble(lowerPriceTextField.getText());

                    // check if product name is within 3-20 characters
                    // if it doesn't
                    productName = lowerNameTextField.getText();
                    if (productName.length() < 3 || productName.length() > 20)
                    {
                        // show warning message to user
                        lowerMessageLabel.setText("Product name must be within 3 to 20 characters");
                    }
                    // then, check if product price is between 0 and 10000
                    // if it isn't
                    else if (productPrice < 0 || productPrice >= 10000)
                    {
                        // show warning message to user
                        lowerMessageLabel.setText("Product price must be within 0 to 10,000.");
                    }
                    // if passed both conditions
                    else
                    {
                        // call updateProduct(int Id, String name, double price)
                        boolean updateResult = FileProcessing.updateProduct(productId, productName, productPrice);
//                        boolean updateResult = true; // test use

                        // if successfully updated
                        if (updateResult)
                        {
                            // show success message to user
                            lowerMessageLabel.setText("Update Successful!");
                        }
                        else
                        {
                            // show a warning message to user
                            lowerMessageLabel.setText("The product doesn't exist!");
                        }
                    }
                }
                catch (Exception ex)
                {
                    lowerMessageLabel.setText("Please ensure you have valid input for all fields.");
                }
            }
        }


        class deleteButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                // parse id into int type
                try {
                    // when parse successfully,
                    productId = Integer.parseInt(lowerIdTextField.getText());

                    // validate input.
                    // if input is not positive integer
                    if (productId <= 0) {
                        lowerMessageLabel.setText("Please enter positive digit for ID!");
                    }
                    // if input is positive integer
                    else
                    {
                        // call deleteProduct(int Id)
                        boolean deleteResult = FileProcessing.deleteProduct(productId);
//                        boolean deleteResult = true; // test use

                        // if successfully deleted
                        if (deleteResult)
                        {
                            // print success message to user
                            String successMsg = String.format("Product with ID %d is deleted", productId);
                            lowerMessageLabel.setText(successMsg);

                            // clear out the text fields
                            lowerNameTextField.setText("");
                            lowerIdTextField.setText("");
                            lowerPriceTextField.setText("");
                        }
                        else
                        {
                            // print warning message to user
                            lowerMessageLabel.setText("The product ID doesn't exist!");
                        }
                    }
                }
                catch (Exception ex)
                {
                    lowerMessageLabel.setText("Please enter digits for ID!");
                }
            }
        }

        // attach listeners to buttons
        ActionListener updateListener = new updateButtonListener();
        updateButton.addActionListener(updateListener);

        ActionListener deleteListener = new deleteButtonListener();
        deleteButton.addActionListener(deleteListener);


        // add elements to panel
        lowerRightPanel.add(lowerMessageLabel, BorderLayout.CENTER);

        JPanel tempPanel = new JPanel();
        tempPanel.add(updateButton);
        tempPanel.add(deleteButton);
        lowerRightPanel.add(tempPanel, BorderLayout.SOUTH);

        // return panel (to lower base panel)
        return lowerRightPanel;

    }

}
