package boundary;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import control.SearchLogic;
import com.jgoodies.*;
import entity.Place;
import enums.AccommodationStyles;
import enums.PriceLevel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;


public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> placeComboBox;
    private JTextField nameTextField;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> cityCodeComboBox;
    private JComboBox<String> accommodationComboBox;
    private JComboBox<String> priceLevelComboBox;
    private DefaultComboBoxModel<String> placeComboBoxModel;
    private JButton addButton;
    private JButton removeButton;
    private JButton confirmButton;
    private JButton cancelButton;
    
    /*
     * Returns all city code to populate relevant comboboxes
     */
    public String[] getUniqueCityCodes() {       
         return SearchLogic.getSearchLogic_Instance().getUniqueCityCodes();      
    }
    
    /*
     * Returns all Kitchen styles to populate relevant comboboxes
     */      
    public String[] getUniqueKitchenStyles() {
 
        return SearchLogic.getSearchLogic_Instance().getUniqueKitchenStyles(); 
    }
    
   /*
    * ------------Frame components---------- --
    */
    
    public MainFrame() {
  
        setTitle("Place Search");
        setSize(935, 590);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for search criteria
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(158, 215, 239));
        searchPanel.setBounds(20, 79, 636, 152);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(30, 8, 46, 14);
        String[] types = {"all","hotel", "restaurant"};
        DefaultComboBoxModel<String> typeComboBoxModel = new DefaultComboBoxModel<>(types);
        typeComboBox = new JComboBox<>(typeComboBoxModel);
        typeComboBox.setBounds(70, 5, 119, 20);
        searchPanel.setLayout(null);
        searchPanel.add(typeLabel);
        searchPanel.add(typeComboBox);  

        JLabel cityCodeLabel = new JLabel("City Code:");
        cityCodeLabel.setBounds(230, 8, 70, 15);
        DefaultComboBoxModel<String> cityCodeComboBoxModel = new DefaultComboBoxModel<>(getUniqueCityCodes());
        cityCodeComboBoxModel.addElement("all");
        cityCodeComboBox = new JComboBox<>(cityCodeComboBoxModel);
        cityCodeComboBox.setBounds(295, 5, 100, 20);
        cityCodeComboBox.setSelectedItem("all");
        searchPanel.add(cityCodeLabel);
        searchPanel.add(cityCodeComboBox);

        JLabel accommodationLabel = new JLabel("Accommodation:");
        accommodationLabel.setBounds(230, 61, 110, 15);
        String[] accommodations = {"all","AI", "BB", "FB", "HB", "RO"};
        DefaultComboBoxModel<String> accommodationComboBoxModel = new DefaultComboBoxModel<>(accommodations);
        accommodationComboBox = new JComboBox<>(accommodationComboBoxModel);
        accommodationComboBox.setBounds(340, 58, 76, 20);
        searchPanel.add(accommodationLabel);
        searchPanel.add(accommodationComboBox);

        JLabel priceLevelLabel = new JLabel("Price Level:");
        priceLevelLabel.setBounds(435, 8, 76, 15);
        String[] priceLevels = {"all","LOW", "MEDIUM", "HIGH"};
        DefaultComboBoxModel<String> priceLevelComboBoxModel = new DefaultComboBoxModel<>(priceLevels);
        getContentPane().setLayout(null);
        priceLevelComboBox = new JComboBox<>(priceLevelComboBoxModel);
        priceLevelComboBox.setBounds(510, 5, 110, 20);
        searchPanel.add(priceLevelLabel);
        searchPanel.add(priceLevelComboBox);

        getContentPane().add(searchPanel);
        
                JLabel nameLabel = new JLabel("Place Name:");
                nameLabel.setBounds(30, 93, 84, 14);
                searchPanel.add(nameLabel);
                nameTextField = new JTextField();
                nameTextField.setBounds(115, 90, 141, 20);
                searchPanel.add(nameTextField);
                
                JButton btnSearch = new JButton("Search");
                btnSearch.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(158, 215, 239), new Color(101, 174, 214)));
                btnSearch.setForeground(new Color(158, 215, 239));
                btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnSearch.setBackground(new Color(101, 174, 214));
                btnSearch.setFocusable(false);
                btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
                btnSearch.setBounds(10, 118, 615, 25);
                searchPanel.add(btnSearch);
                
                JLabel lblstarsLabel = new JLabel("Star Rating:");
                lblstarsLabel.setBounds(30, 62, 70, 15);
                searchPanel.add(lblstarsLabel);
                String[] stars = {"all","1", "2", "3", "4", "5"};
                DefaultComboBoxModel<String> ratingStarsComboBoxModel = new DefaultComboBoxModel<>(stars);
                JComboBox ratingComboBox = new JComboBox<>(ratingStarsComboBoxModel);
                ratingComboBox.setBounds(115, 58, 56, 22);
          
                
                searchPanel.add(ratingComboBox);
                
                JLabel lblKitchenStyleLabel = new JLabel("Kitchen Style:");
                lblKitchenStyleLabel.setBounds(30, 36, 99, 15);
                searchPanel.add(lblKitchenStyleLabel);
                
                DefaultComboBoxModel<String> StylesComboBoxModel = new DefaultComboBoxModel<>(getUniqueKitchenStyles());
                StylesComboBoxModel.addElement("all");
                JComboBox kitchenStylescomboBox = new JComboBox<>(StylesComboBoxModel);                
                kitchenStylescomboBox.setBounds(115, 32, 141, 22);
                kitchenStylescomboBox.setSelectedItem("all");
                searchPanel.add(kitchenStylescomboBox);
                
                
                btnSearch.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {               		
                	 String	type = (String) typeComboBox.getSelectedItem();
                	 String placeName = nameTextField.getText();
       				 String cityCode=(String) cityCodeComboBox.getSelectedItem();      				       				       			
       				String selectedStarRating = (String) ratingComboBox.getSelectedItem();
       				Integer starRating = null;
       				if (!"all".equals(selectedStarRating)) {
       				    starRating = Integer.parseInt(selectedStarRating);
       				}      			     		       				
       				String selectedPriceLevelString = (String) priceLevelComboBox.getSelectedItem();
       				PriceLevel priceLevel = null;
       				if (!"all".equals(selectedPriceLevelString)) {
       				    priceLevel = PriceLevel.valueOf(selectedPriceLevelString.toUpperCase());
       				}
       				
       				 String kitchenStyle = (String)kitchenStylescomboBox.getSelectedItem();
       				  				
       				String selectedAccommodationStyleString = (String) accommodationComboBox.getSelectedItem();
       				AccommodationStyles accommodationStyle = null;
       				if (!"all".equals(selectedAccommodationStyleString)) {
       					accommodationStyle = AccommodationStyles.valueOf(selectedAccommodationStyleString.toUpperCase());
       				}
 
       				List<Place> filteredPlaces = SearchLogic.getSearchLogic_Instance().searchPlaceFilter(type, placeName, cityCode, priceLevel, accommodationStyle, starRating, kitchenStyle);
       				placeComboBox.removeAllItems();

       			        
       				// Add filtered places to the ComboBox
       		        for (Place place : filteredPlaces) {
       		            placeComboBox.addItem(place.toString());
       		        }
       				 if(placeComboBox.getItemAt(0) == null) {
       					 JOptionPane.showMessageDialog(MainFrame.this, "No Matches :(", "Search Results", JOptionPane.INFORMATION_MESSAGE);
       				 }
       				placeComboBox.revalidate();
       				placeComboBox.repaint();    		
                	}
                });

                
               //Added Places TextPane: 
                JTextPane AddedtextPane = new JTextPane();
                AddedtextPane.setBackground(new Color(101, 174, 214));
                AddedtextPane.setBounds(660, 79, 221, 363);
                AddedtextPane.setEditable(false);
                getContentPane().add(AddedtextPane);            

        // Initialize ComboBox for displaying search results
        placeComboBoxModel = new DefaultComboBoxModel<>();
                
                JButton btnResetFilter = new JButton("Reset Filters");
                btnResetFilter.setForeground(new Color(158, 215, 239));
                btnResetFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnResetFilter.setBackground(new Color(101, 174, 214));
                btnResetFilter.setFocusable(false);
                btnResetFilter.setBounds(20, 46, 151, 23);
                getContentPane().add(btnResetFilter);
                btnResetFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Reset all search panel combo boxes
                        typeComboBox.setSelectedItem("all");
                        nameTextField.setText("");
                        cityCodeComboBox.setSelectedItem("all");
                        accommodationComboBox.setSelectedItem("all");
                        priceLevelComboBox.setSelectedItem("all");
                        ratingComboBox.setSelectedItem("all");
                        kitchenStylescomboBox.setSelectedItem("all"); 
                    }
                });
                              
                
                JLabel lblChosenLabel = new JLabel("Chosen to be Added to your trip:");
                lblChosenLabel.setOpaque(true);
                lblChosenLabel.setBackground(new Color(101, 174, 214));
                lblChosenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                lblChosenLabel.setHorizontalTextPosition(SwingConstants.LEADING);
                lblChosenLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
                lblChosenLabel.setBounds(660, 62, 221, 14);
                lblChosenLabel.setForeground(new Color(158, 215, 239));
                getContentPane().add(lblChosenLabel);
                confirmButton = new JButton("Confirm");
                confirmButton.setForeground(new Color(101, 174, 214));
                confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                confirmButton.setBackground(new Color(158, 215, 239));
                confirmButton.setFocusable(false);
                confirmButton.setBounds(660, 460, 221, 43);
                confirmButton.setFont(new Font("Tahoma", Font.BOLD, 25));
                getContentPane().add(confirmButton);
                cancelButton = new JButton("Cancel");
                cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                cancelButton.setBackground(new Color(101, 174, 214));
                cancelButton.setFocusable(false);
                cancelButton.setBounds(20, 490, 111, 30);
                getContentPane().add(cancelButton);
                
                JPanel panel = new JPanel();
                panel.setBounds(0, 0, 921, 542);
                getContentPane().add(panel);
                panel.setLayout(null);
        
        JLabel lblResultsLabel = new JLabel("Search Results:");
        lblResultsLabel.setBounds(23, 246, 111, 14);
        panel.add(lblResultsLabel);
        lblResultsLabel.setForeground(new Color(101, 174, 214));
        lblResultsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
                
        // Panel for Buttons:
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(20, 299, 636, 51);
        panel.add(buttonPanel);
        removeButton = new JButton("Remove Place");
        removeButton.setForeground(new Color(158, 215, 239));
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.setBackground(new Color(101, 174, 214));
        removeButton.setFocusable(false);
        removeButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        removeButton.setBounds(300, 5, 140, 40);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected place from placeComboBox
                String selectedPlace = (String) placeComboBox.getSelectedItem();

                // Get the current text from AddedTextPane
                String currentText = AddedtextPane.getText();

                // Remove the selected place's text from AddedTextPane
                String updatedText = currentText.replace(selectedPlace + "\n", "");
                AddedtextPane.setText(updatedText);
            }
        });
        
                buttonPanel.add(removeButton);
                
                        buttonPanel.setLayout(null);
                        
                                addButton = new JButton("Add Place");
                                addButton.setForeground(new Color(158, 215, 239));
                                addButton.setPreferredSize(new Dimension(80, 40));
                                addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                addButton.setBackground(new Color(101, 174, 214));
                                addButton.setFocusable(false);
                                addButton.setFont(new Font("Tahoma", Font.BOLD, 14));
                                addButton.setBounds(140, 5, 140, 40);
                                buttonPanel.add(addButton);
                                
                        // Action Listeners
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the selected place from placeComboBox
                        String selectedPlace = (String) placeComboBox.getSelectedItem();
                        
                        // Add the selected place's details to the AddedtextPane
                        if (selectedPlace!=null)
                        AddedtextPane.setText(AddedtextPane.getText() + selectedPlace + "\n");
                    }
                });
                placeComboBox = new JComboBox<>(placeComboBoxModel);
                placeComboBox.setBounds(20, 265, 636, 23);
                panel.add(placeComboBox);
                
                JLabel lblNewLabel = new JLabel("New label");
                lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/boundary/perfect.png")));
                lblNewLabel.setBounds(0, 0, 921, 555);
                panel.add(lblNewLabel);
                
                        cancelButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                             
                                dispose(); // Close the window
                            }
                        });
                
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            	if(AddedtextPane.getText()!=" ")
              					 JOptionPane.showMessageDialog(MainFrame.this, "Have a Great Trip!\n review your choices and we'll meet again at the next excercise\n Press 'Cancel' to close window...", "finish", JOptionPane.INFORMATION_MESSAGE);
                               
                            }
                        });
                        
                        // Action listener for typeComboBox To show only relevant Places
                        typeComboBox.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String selectedType = (String) typeComboBox.getSelectedItem();
                                
                                
                                if ("hotel".equals(selectedType)) {
                                	lblKitchenStyleLabel.setVisible(false);
                                    kitchenStylescomboBox.setVisible(false);
                                    lblstarsLabel.setVisible(true);
                                    ratingComboBox.setVisible(true);
                                    accommodationLabel.setVisible(true);
                                    accommodationComboBox.setVisible(true);
                                } 
                               
                                if ("restaurant".equals(selectedType)) {
                                	lblKitchenStyleLabel.setVisible(true);
                                    kitchenStylescomboBox.setVisible(true);
                                    lblstarsLabel.setVisible(false);
                                    ratingComboBox.setVisible(false);
                                    accommodationLabel.setVisible(false);
                                    accommodationComboBox.setVisible(false);

                                } 
                           
                                // All shows all:
                                if ("all".equals(selectedType)) {
                                    lblKitchenStyleLabel.setVisible(true);
                                    kitchenStylescomboBox.setVisible(true);                              
                                    lblKitchenStyleLabel.setVisible(true);
                                    kitchenStylescomboBox.setVisible(true);
                                    accommodationLabel.setVisible(true);
                                    accommodationComboBox.setVisible(true);
                                    lblstarsLabel.setVisible(true);
                                    ratingComboBox.setVisible(true);
                                }
                            }
                        });

    }


    /**
	 * MAIN TO RUN
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	        SearchLogic searchLogic = SearchLogic.getSearchLogic_Instance();
 
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					//TEST TO SEE PLACES:
					System.out.println(searchLogic.getPlaces());					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

