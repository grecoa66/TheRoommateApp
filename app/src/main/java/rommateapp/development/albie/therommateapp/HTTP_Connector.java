package rommateapp.development.albie.therommateapp;

/**
 * Created by Matthew on 10/20/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.os.AsyncTask;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class HTTP_Connector extends Activity {

    AlertDialog alertDialog;
    Context ctx;

    HTTP_Connector(Context ctx) {
        this.ctx = ctx;
    }

    class getUser extends AsyncTask<String, String, String> {
        String useid = "";
        String firstname = "";
        String lastname = "";
        String emailaddr = "";
        String phonenum = "";
        AsyncResponse delegate;
        public getUser(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";
            try {
                //Create connection
                String email = params[0];
                String password = params[1];

                String urlParameters = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_user.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            String[] parts = result.split("-");
            String uid = parts[0]; //
            String fname = parts[1];
            String lname = parts[2];
            String email = parts[3];
            String phone = parts[4];

            useid = uid;
            firstname = fname;
            lastname = lname;
            emailaddr = email;
            phonenum = phone;

            int id = Integer.valueOf(useid.trim());
            delegate.processFinish(new User(id, firstname, lastname, emailaddr, phonenum));
        }

        protected void onPreExecute(String result) {
            // something...
        }

        public User createUserObject() {
            int userId = Integer.valueOf(useid);
            User user = new User(userId, firstname, lastname, emailaddr, phonenum);
            return user;
        }
    }


    class getGroup extends AsyncTask<String, String, String> {
        /**
         * @param params
         * @return
         */

        protected String doInBackground(String... params) {
            String response = "";
          /*  try {
                //Create connection
                String email = params[0];
                String password = params[1];

                String urlParameters = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_user.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            */
            return response;
        }

        protected void onPostExecute(String result) {
            /**
             * TODO
             */
        }

        protected void onPreExecute(String result) {
            // something...
        }

       /* public User createGroupObject() {

        }
        */
    }


    class getChoreList extends AsyncTask<String, String, String> {
        int chore_list_id;
        ArrayList<Chore> chores = new ArrayList<>();
        private AsyncResponse delegate;
        public getChoreList(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";
            try {
                //Create connection
                String groupid = params[0];

                String urlParameters = "groupid=" + URLEncoder.encode(groupid, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_chore_list.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
            // Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            try {
                JSONArray json = new JSONArray(result);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject json_obj = json.getJSONObject(i);
                    String id = json_obj.get("id").toString();
                    String name = json_obj.get("name").toString();
                    String desc = json_obj.get("desc").toString();
                    String assigned_to = json_obj.get("assigned_to").toString();
                    String point_val = json_obj.get("point_val").toString();
                    String posted_by = json_obj.get("posted_by").toString();
                    String completed_by = json_obj.get("completed_by").toString();
                    String groupid = json_obj.get("groupid").toString();
                    int c_id = Integer.valueOf(id);
                    int g_id = Integer.valueOf(groupid);
                    boolean iscomp = false;
                    if (completed_by.length() > 0) {
                        iscomp = true;
                    }
                    Chore chre = new Chore(name, desc, posted_by, assigned_to, iscomp, g_id);
                    chre.setId(c_id);

                    chores.add(chre);
                }
                delegate.processFinish(chores);
                // Toast.makeText(ctx, "chores arraylist " + chores.toString(), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        protected void onPreExecute(String result) {
            // something...
        }

        public ArrayList<Chore> createChoreListObject() {
            Toast.makeText(ctx, "chores arraylist " + chores.toString(), Toast.LENGTH_LONG).show();
            return chores;
        }
    }


    class addChore extends AsyncTask<Chore, String, String> {
        protected String doInBackground(Chore... params) {
            String response = "";
            try {
                Chore choreobj = params[0];
                int groupid = choreobj.groupid;
                String g_id = Integer.toString(groupid);
                String title = choreobj.title;
                String desc = choreobj.desc;
                String requestUser = choreobj.requestUser;
                String assignedUser = choreobj.assignedUser;

                String urlParameters = "title=" + URLEncoder.encode(title, "UTF-8") + "&desc=" + URLEncoder.encode(desc, "UTF-8")
                        + "&requestUser=" + URLEncoder.encode(requestUser, "UTF-8")
                        + "&assignedUser=" + URLEncoder.encode(assignedUser, "UTF-8")
                        + "&groupid=" + URLEncoder.encode(g_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/add_chore.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }


    class editChore extends AsyncTask<Chore, String, String> {
        protected String doInBackground(Chore... params) {
            String response = "";
            String c_id = "";
            try {
                Chore choreobj = params[0];
                int choreid = choreobj.getChoreId();
                c_id = Integer.toString(choreid);
                String title = choreobj.title;
                String desc = choreobj.desc;
                String requestUser = choreobj.requestUser;
                String assignedUser = choreobj.assignedUser;

                String urlParameters = "title=" + URLEncoder.encode(title, "UTF-8")
                        + "&desc=" + URLEncoder.encode(desc, "UTF-8")
                        + "&requestUser=" + URLEncoder.encode(requestUser, "UTF-8")
                        + "&assignedUser=" + URLEncoder.encode(assignedUser, "UTF-8")
                        + "&choreid=" + URLEncoder.encode(c_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/edit_chore.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }



    class deleteChore extends AsyncTask<Integer, String, String> {
        protected String doInBackground(Integer... params) {
            String response = "";

            try {
                int c_id = params[0];
                String chore_id = Integer.toString(c_id);


                String urlParameters = "choreid=" + URLEncoder.encode(chore_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/delete_chore.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }





    class getGroceryList extends AsyncTask<String, String, String> {
        ArrayList<Grocery> gcry = new ArrayList<>();
        private AsyncResponse delegate;
        public getGroceryList(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";

            try {
                String groupid = params[0];

                String urlParameters = "groupid=" + URLEncoder.encode(groupid, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_grocery_list.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }
        protected void onPostExecute(String result) {
            try {
                JSONArray json = new JSONArray(result);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject json_obj = json.getJSONObject(i);
                    String id = json_obj.get("id").toString();
                    String item_name = json_obj.get("item_name").toString();
                    String quantity = json_obj.get("quantity").toString();
                    String dateRequested = json_obj.get("dateRequested").toString();
                    String datePurchased = json_obj.get("datePurchased").toString();
                    String isPurchased = json_obj.get("isPurchased").toString();
                    String costPerItem = json_obj.get("cost").toString();
                    String requestUser = json_obj.get("requestUser").toString();
                    String purchaseUser = json_obj.get("purchaseUser").toString();
                    int grc_id = Integer.valueOf(id);
                    int quant = Integer.valueOf(quantity);
                    Double cost_per_Item = Double.valueOf(costPerItem);
                    boolean isPurchsed = false;
                    if (isPurchased.length() > 0) {
                        isPurchsed = true;
                    }
                    Grocery grocery = new Grocery(grc_id, item_name, quant, dateRequested, datePurchased, isPurchsed, cost_per_Item, requestUser, purchaseUser);
                    gcry.add(grocery);
                }
                GroceryList grcy_list = new GroceryList(gcry);
                delegate.processFinish(grcy_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class addGrocery extends AsyncTask<Grocery, String, String> {
        protected String doInBackground(Grocery... params) {
            String response = "";
            try {
                Grocery choreobj = params[0];
                String item_name = choreobj.itemName;
                int quantity = choreobj.quantity;
                String quant = Integer.toString(quantity);
                String dr = choreobj.dateRequested;
                String dp = choreobj.datePurchased;
                boolean ispurchased = choreobj.isPurchased;
                String ip = Boolean.toString(ispurchased);
                Double cost = choreobj.costPerItem;
                String cst = Double.toString(cost);
                String requestUser = choreobj.requestUser;
                String purchaseUser = choreobj.purchaseUser;
                int groupid = choreobj.groupid;
                String g_id = Integer.toString(groupid);

                String urlParameters = "item_name=" + URLEncoder.encode(item_name, "UTF-8") + "&quantity=" + URLEncoder.encode(quant, "UTF-8")
                        + "&dr=" + URLEncoder.encode(dr, "UTF-8")
                        + "&dp=" + URLEncoder.encode(dp, "UTF-8")
                        + "&ip=" + URLEncoder.encode(ip, "UTF-8")
                        + "&cst=" + URLEncoder.encode(cst, "UTF-8")
                        + "&requestUser=" + URLEncoder.encode(requestUser, "UTF-8")
                        + "&purchaseUser=" + URLEncoder.encode(purchaseUser, "UTF-8")
                        + "&groupid=" + URLEncoder.encode(g_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/add_grocery.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }






    class editGrocery extends AsyncTask<Grocery, String, String> {
        protected String doInBackground(Grocery... params) {
            String response = "";
            try {
                Grocery choreobj = params[0];
                String item_name = choreobj.itemName;

                int quantity = choreobj.quantity;
                String quant = Integer.toString(quantity);

                String dr = choreobj.dateRequested;

                boolean ispurchased = choreobj.isPurchased;
                String ip = Boolean.toString(ispurchased);

                Double cost = choreobj.costPerItem;
                String cst = Double.toString(cost);

                String requestUser = choreobj.requestUser;
                String purchaseUser = choreobj.purchaseUser;

                int groceryid = choreobj.groceryId;
                String grc_id = Integer.toString(groceryid);


                String urlParameters = "item_name=" + URLEncoder.encode(item_name, "UTF-8") + "&quantity=" + URLEncoder.encode(quant, "UTF-8")
                        + "&dr=" + URLEncoder.encode(dr, "UTF-8")
                        + "&ip=" + URLEncoder.encode(ip, "UTF-8")
                        + "&cst=" + URLEncoder.encode(cst, "UTF-8")
                        + "&requestUser=" + URLEncoder.encode(requestUser, "UTF-8")
                        + "&purchaseUser=" + URLEncoder.encode(purchaseUser, "UTF-8")
                        + "&grcid=" + URLEncoder.encode(grc_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/edit_grocery.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
         //   Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }



    class deleteGrocery extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {
            String response = "";
            try {
                String grc_id = params[0];

                String urlParameters = "grcid=" + URLEncoder.encode(grc_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/edit_grocery.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
           // Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }








    class getMaintenanceList extends AsyncTask<String, String, String> {
        ArrayList<MaintenanceItem> mnt_itm = new ArrayList<>();
        private AsyncResponse delegate;
        public getMaintenanceList(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";

            try {
                String groupid = params[0];

                String urlParameters = "groupid=" + URLEncoder.encode(groupid, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_maintenance_list.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
            catch (IOException ex) {

               Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }
        protected void onPostExecute(String result) {
            try {
                JSONArray json = new JSONArray(result);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject json_obj = json.getJSONObject(i);
                    String id = json_obj.get("id").toString();
                    String desc = json_obj.get("desc").toString();
                    String causingUser = json_obj.get("causingUser").toString();
                    String purchaseUser = json_obj.get("purchaseUser").toString();
                    String isComplete = json_obj.get("isComplete").toString();
                    String groupid = json_obj.get("groupid").toString();

                    int mnt_id = Integer.valueOf(id);
                    int isComp = Integer.valueOf(isComplete);
                    boolean isDone = false;
                    if (isComp == 0) {
                        isDone = true;
                    }
                    MaintenanceItem maint_item = new MaintenanceItem(mnt_id, desc, causingUser, purchaseUser, Integer.valueOf(groupid), isDone);
                    mnt_itm.add(maint_item);
                }
                MaintenanceList mainten_list = new MaintenanceList(mnt_itm);
                delegate.processFinish(mainten_list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




    class addMaintenanceItem extends AsyncTask<MaintenanceItem, String, String> {
        protected String doInBackground(MaintenanceItem... params) {
            String response = "";
            try {
                MaintenanceItem mntce_obj = params[0];
                String desc = mntce_obj.desc;
                String causingUser = mntce_obj.causingUser;
                String purchaseUser = mntce_obj.purchaseUser;
                Boolean isComplete = mntce_obj.isComplete;
                String isComplet = isComplete.toString();

                int groupid = mntce_obj.groupid;
                String g_id = Integer.toString(groupid);

                String urlParameters = "desc=" + URLEncoder.encode(desc, "UTF-8")
                        + "&causingUser=" + URLEncoder.encode(causingUser, "UTF-8")
                        + "&purchaseuser=" + URLEncoder.encode(purchaseUser, "UTF-8")
                        + "&isComplete=" + URLEncoder.encode(isComplet, "UTF-8")
                        + "&groupid=" + URLEncoder.encode(g_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/add_maintenance_item.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();


                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();
            } catch (MalformedURLException ex) {
                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();

            }
// and some more
            catch (IOException ex) {

                Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }


}