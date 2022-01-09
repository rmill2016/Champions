package champDB;

import javax.swing.JFrame;
import javax.ws.rs.*;
import java.awt.EventQueue;
import javax.ws.rs.core.MediaType;
import com.mongodb.*;
import com.mongodb.client.*;
import org.json.*;
import java.net.*;
import java.io.*;
import org.bson.*;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Path("/")
public class Champs {

		private Common common;
		
		private JFrame frame;
		private JTextField CNField;
		private JTextField Skill1;
		private JTextField Skill2;
		private JTextField Skill3;
		private JTextField Skill4;
		private JTextArea textArea;
		
		
		// launch application test
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Champs window = new Champs();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		//create the application
		
		
		
		public Champs() {
			common = new Common();
			initialize();
		}
		
		private String getRestURL(String server) {
			String url = "http://" + server + ":8080/champion";
			return url;
		}
		
		private String getRestURL(String server, String category, String param1) {
			String url = "http://" + server + ":8080/ChampionFinal/" + category + "/" + param1;
			return url;
		}
						
		//get champion JSON data
		private String getData(String restURL) {
			String ret = "";
			try {
				URL url = new URL(restURL);
				URLConnection urlConn = url.openConnection();
				InputStream inputStream = urlConn.getInputStream();
				BufferedReader in = new BufferedReader
							(new InputStreamReader(inputStream));
				String line = in.readLine();
				in.close();
				
				//parse JSON data
				JSONObject obj = new JSONObject(line);
				JSONArray champion = obj.getJSONArray("champion");
				for (int i=0; i<champion.length(); i++)
				{
					JSONObject entry = champion.getJSONObject(i);
					ret += "champName: " + entry.getString("champName") + ", ";
					ret += "skill1: " + entry.getString("skill1") + ", ";
					ret += "skill2: " + entry.getString("skill2") + ", ";
					ret += "skill3: " + entry.getString("skill3") + ", ";
					ret += "skill4: " + entry.getString("skill4") + ", ";
					ret += "role: " + entry.getString("role") + ", ";
					ret += "type: " + entry.getString("type") + ", ";
					ret += "dmgType: " + entry.getString("dmgType") + "\n";
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		
			return ret;
		}
		
		private void initialize() {
			frame = new JFrame();
			frame.setBackground(new Color(100, 149, 237));
			frame.getContentPane().setBackground(new Color(100, 149, 237));
			frame.getContentPane().setLayout(null);
			
			
			JLabel Title = new JLabel("Know Your Champion");
			Title.setBounds(0, 0, 584, 25);
			Title.setFont(new Font("Tahoma", Font.PLAIN, 24));
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(Title);
			
			JLabel CN = new JLabel("Champion Name:");
			CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
			CN.setBounds(10, 44, 132, 25);
			frame.getContentPane().add(CN);
			
			CNField = new JTextField();
			CNField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			CNField.setForeground(new Color(0, 0, 0));
			CNField.setBounds(128, 48, 201, 20);
			frame.getContentPane().add(CNField);
			CNField.setColumns(10);
			
			JLabel lblRole = new JLabel("Role:");
			lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRole.setBounds(10, 80, 40, 25);
			frame.getContentPane().add(lblRole);
			
			JLabel lblType = new JLabel("Type:");
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblType.setBounds(10, 124, 40, 25);
			frame.getContentPane().add(lblType);
			
			JLabel lblSkill1 = new JLabel("Skill 1:");
			lblSkill1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSkill1.setBounds(10, 178, 132, 25);
			frame.getContentPane().add(lblSkill1);
			
			JLabel lblSkill2 = new JLabel("Skill 2:");
			lblSkill2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSkill2.setBounds(10, 228, 132, 25);
			frame.getContentPane().add(lblSkill2);
			
			JLabel lblSkill3 = new JLabel("Skill 3:");
			lblSkill3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSkill3.setBounds(10, 282, 132, 25);
			frame.getContentPane().add(lblSkill3);
			
			JLabel lblSkill4 = new JLabel("Skill 4:");
			lblSkill4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSkill4.setBounds(10, 338, 132, 25);
			frame.getContentPane().add(lblSkill4);
			
			JLabel lblDamageType = new JLabel("Damage Type:");
			lblDamageType.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDamageType.setBounds(10, 388, 132, 25);
			frame.getContentPane().add(lblDamageType);
			
			JButton Top = new JButton("Top");
			Top.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String server = "35.227.165.117";
					String role =  "Top"; 
					String restURL = getRestURL(server, "role", role);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Top.setForeground(new Color(0, 0, 0));
			Top.setBackground(new Color(255, 215, 0));
			Top.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Top.setBounds(59, 83, 82, 23);
			frame.getContentPane().add(Top);
			
			JButton Jungle = new JButton("Jungle");
			Jungle.setBackground(new Color(255, 215, 0));
			Jungle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String server = "35.227.165.117";
					String role =  "Jungle"; 
					String restURL = getRestURL(server, "role", role);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Jungle.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Jungle.setBounds(153, 82, 82, 23);
			frame.getContentPane().add(Jungle);
			
			JButton Mid = new JButton("Mid");
			Mid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String role =  "Mid"; 
					String restURL = getRestURL(server, "role", role);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Mid.setBackground(new Color(255, 215, 0));
			Mid.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Mid.setBounds(245, 82, 82, 23);
			frame.getContentPane().add(Mid);
			
			JButton Bot = new JButton("Bot");
			Bot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String role =  "Bot"; 
					String restURL = getRestURL(server, "role", role);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Bot.setBackground(new Color(255, 215, 0));
			Bot.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Bot.setBounds(337, 82, 82, 23);
			frame.getContentPane().add(Bot);
			
			JButton Support = new JButton("Support");
			Support.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String role =  "Support"; 
					String restURL = getRestURL(server, "role", role);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Support.setBackground(new Color(255, 215, 0));
			Support.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Support.setBounds(428, 81, 102, 23);
			frame.getContentPane().add(Support);
			
			JButton Tank = new JButton("Tank");
			Tank.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String type =  "Tank"; 
					String restURL = getRestURL(server, "type", type);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Tank.setBackground(new Color(255, 215, 0));
			Tank.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Tank.setBounds(60, 127, 82, 23);
			frame.getContentPane().add(Tank);
			
			JButton Adc = new JButton("ADC");
			Adc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String type =  "ADC"; 
					String restURL = getRestURL(server, "type", type);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Adc.setBackground(new Color(255, 215, 0));
			Adc.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Adc.setBounds(153, 127, 82, 23);
			frame.getContentPane().add(Adc);
			
			JButton Assassin = new JButton("Assassin");
			Assassin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String type =  "Assassin"; 
					String restURL = getRestURL(server, "type", type);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Assassin.setBackground(new Color(255, 215, 0));
			Assassin.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Assassin.setBounds(247, 127, 108, 23);
			frame.getContentPane().add(Assassin);
			
			JButton Mage = new JButton("Mage");
			Mage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String type =  "Mage"; 
					String restURL = getRestURL(server, "type", type);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			Mage.setBackground(new Color(255, 215, 0));
			Mage.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Mage.setBounds(365, 125, 82, 23);
			frame.getContentPane().add(Mage);
			
			Skill1 = new JTextField();
			Skill1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Skill1.setBackground(new Color(255, 255, 255));
			Skill1.setBounds(59, 182, 89, 20);
			frame.getContentPane().add(Skill1);
			Skill1.setColumns(10);
			
			Skill2 = new JTextField();
			Skill2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Skill2.setColumns(10);
			Skill2.setBounds(59, 232, 89, 20);
			frame.getContentPane().add(Skill2);
			
			Skill3 = new JTextField();
			Skill3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Skill3.setColumns(10);
			Skill3.setBounds(59, 286, 89, 20);
			frame.getContentPane().add(Skill3);
			
			Skill4 = new JTextField();
			Skill4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			Skill4.setColumns(10);
			Skill4.setBounds(59, 342, 89, 20);
			frame.getContentPane().add(Skill4);
			
			JButton AD = new JButton("AD");
			AD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String damage =  "AD"; 
					String restURL = getRestURL(server, "damage", damage);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			AD.setBackground(new Color(255, 215, 0));
			AD.setFont(new Font("Tahoma", Font.PLAIN, 14));
			AD.setBounds(113, 391, 82, 23);
			frame.getContentPane().add(AD);
			
			JButton AP = new JButton("AP");
			AP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String damage =  "AP"; 
					String restURL = getRestURL(server, "damage", damage);
					System.out.println(restURL);
					textArea.setText(getData(restURL));
				}
			});
			AP.setBackground(new Color(255, 215, 0));
			AP.setFont(new Font("Tahoma", Font.PLAIN, 14));
			AP.setBounds(113, 425, 82, 23);
			frame.getContentPane().add(AP);
			
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textArea.setBounds(257, 189, 317, 259);
			frame.getContentPane().add(textArea);
			
			JLabel lblYourChampion = new JLabel("Your Champion");
			lblYourChampion.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblYourChampion.setHorizontalAlignment(SwingConstants.CENTER);
			lblYourChampion.setBounds(337, 161, 173, 25);
			frame.getContentPane().add(lblYourChampion);
			
			JButton btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String server = "35.227.165.117";
					String name =  CNField.getText();
					String restURL = getRestURL(server, "name", name);
					textArea.setText(getData(restURL));
					
					
				}
			});
			btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSubmit.setBounds(351, 47, 89, 23);
			frame.getContentPane().add(btnSubmit);
			
			JButton btnSubmit1 = new JButton("Submit");
			btnSubmit1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String server = "35.227.165.117";
					String skill =  Skill1.getText();
					String restURL = getRestURL(server, "skill1", skill);
					textArea.setText(getData(restURL));
					
				}
			});
			btnSubmit1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSubmit1.setBounds(158, 181, 89, 23);
			frame.getContentPane().add(btnSubmit1);
			
			JButton btnSubmit2 = new JButton("Submit");
			btnSubmit2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String skill =  Skill2.getText();
					String restURL = getRestURL(server, "skill2", skill);
					textArea.setText(getData(restURL));
				}
			});
			btnSubmit2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSubmit2.setBounds(158, 229, 89, 23);
			frame.getContentPane().add(btnSubmit2);
			
			JButton btnSubmit3 = new JButton("Submit");
			btnSubmit3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String skill =  Skill3.getText();
					String restURL = getRestURL(server, "skill3", skill);
					textArea.setText(getData(restURL));
				}
			});
			btnSubmit3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSubmit3.setBounds(158, 285, 89, 23);
			frame.getContentPane().add(btnSubmit3);
			
			JButton btnSubmit4 = new JButton("Submit");
			btnSubmit4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String server = "35.227.165.117";
					String skill =  Skill4.getText();
					String restURL = getRestURL(server, "skill4", skill);
					textArea.setText(getData(restURL));
				}
			});
			btnSubmit4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSubmit4.setBounds(158, 341, 89, 23);
			frame.getContentPane().add(btnSubmit4);
			frame.setBounds(100, 100, 600, 511);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		

		
		private MongoCollection<Document> getCollection() {
			return common.getCollection();
		}
		
		private String getJsonData(MongoCursor<Document> cursor) {
			String jsonData = "";
			
			try {
				while (cursor.hasNext()) {
					Document doc = cursor.next();
					System.out.println(doc.toJson());
					jsonData += doc.toJson();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
			common.closeConnection();
			System.out.println(jsonData);
			return jsonData;
		}
// show all	

@GET
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public String getChamps() {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find().projection(bson).iterator();
			
	
	return getJsonData(cursor);
}


// show champs with role 
		
@GET
@Path("/role/{role}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsByRole(@PathParam("role") String role) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("role", role)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}

// show champs with type

@GET
@Path("/type/{type}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsByType(@PathParam("type") String type) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("type", type)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}

// show champs by skill1

@GET
@Path("/skill1/{skill1}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsBySkill1(@PathParam("skill1") String skill1) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("skill1", skill1)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}

// show champs by skill2

@GET
@Path("/skill2/{skill2}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsBySkill2(@PathParam("skill2") String skill2) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("skill2", skill2)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}

// show champs by skill3

@GET
@Path("/skill3/{skill3}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsBySkill3(@PathParam("skill3") String skill3) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("skill3", skill3)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}

// show champs by skill4

@GET
@Path("/skill4/{skill4}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsBySkill4(@PathParam("skill4") String skill4) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("skill4", skill4)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}

// show champs by name String

@GET
@Path("/name/{champName}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsByName(@PathParam("champName") String champName) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("champName", champName)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}

// show champs by damage type


@GET
@Path("/damage/{dmgType}")
@Produces(MediaType.APPLICATION_JSON)
public String getChampsByDamage(@PathParam("dmgType") String dmgType) {
	String fields = "{_id:0}";
	
	Bson bson = BasicDBObject.parse(fields);
	MongoCursor<Document> cursor =
			getCollection().find(eq("dmgType", dmgType)
					
					).projection(bson).iterator();
			
	
	return getJsonData(cursor);



}
}
