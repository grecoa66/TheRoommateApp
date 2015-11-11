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

    //user stuff

    class getUser extends AsyncTask<String, String, String> {
        AsyncResponse delegate;
        User user;
        public getUser(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";
            try {
                String deviceid = params[0];
                String urlParameters = "deviceid=" + URLEncoder.encode(deviceid, "UTF-8");
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
            try {
                JSONArray json = new JSONArray(result);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject json_obj = json.getJSONObject(i);
                    String id = json_obj.get("id").toString();
                    String fname = json_obj.get("first_name").toString();
                    String lname = json_obj.get("last_name").toString();
                    String email = json_obj.get("email").toString();
                    String phone_num = json_obj.get("phone_number").toString();
                    String groupid = json_obj.get("group_id").toString();
                    int u_id = Integer.valueOf(id);
                    int g_id = Integer.valueOf(groupid);
                    user = new User(u_id, fname, lname, email, phone_num, g_id);
                }

                delegate.processFinish(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    class getUserList extends AsyncTask<String, String, String> {
        /**
         * @param params
         * @return
         */
        private AsyncResponse delegate;
        UserList userlist = new UserList();
        public getUserList(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";
            try {
                //Create connection
                String groupid = params[0];
                String urlParameters = "groupid=" + URLEncoder.encode(groupid, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_user_list.php");
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
                    String fname = json_obj.get("first_name").toString();
                    String lname = json_obj.get("last_name").toString();
                    String email = json_obj.get("email").toString();
                    String phone_num = json_obj.get("phone_number").toString();
                    String groupid = json_obj.get("group_id").toString();
                    int u_id = Integer.valueOf(id);
                    int g_id = Integer.valueOf(groupid);
                   User user = new User(u_id, fname, lname, email, phone_num, g_id);
                    userlist.addUser(user);
                }
                delegate.processFinish(userlist);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    //group Stuff

class getGroup extends AsyncTask<String, String, String>{

    /**
     * @param params
     * @return
     */
    private AsyncResponse delegate;
    UserList userlist = new UserList();
    BillList bill_list = new BillList();
    public getGroup(AsyncResponse resp){
        delegate = resp;
    }
    protected String doInBackground(String... params) {
        String response = "";
        try {
            //Create connection
            String groupid = params[0];
            String urlParameters = "groupid=" + URLEncoder.encode(groupid, "UTF-8");
            URL url = new URL("http://104.236.10.133/get_group.php");
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

    }


}

    //Chore stuff

    class getChoreList extends AsyncTask<String, String, String> {
        int chore_list_id;
        ChoreList cl = new ChoreList();
       // ArrayList<Chore> chores = new ArrayList<>();
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
                    String assigned_to = json_obj.get("assigned_to").toString().trim();
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
                    cl.addChore(chre);

                }
                delegate.processFinish(cl);
                // Toast.makeText(ctx, "chores arraylist " + chores.toString(), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


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

    //Grocery Stuff

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
                    if (isPurchased.equals("true")) {
                        isPurchsed = true;
                    }
                    Grocery grocery = new Grocery(grc_id, item_name, quant, dateRequested, datePurchased, isPurchsed, cost_per_Item, requestUser, purchaseUser);
                    if (!grocery.isPurchased){
                        gcry.add(grocery);
                    }
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
                        + "&dp=" + URLEncoder.encode("", "UTF-8")
                        + "&ip=" + URLEncoder.encode(ip, "UTF-8")
                        + "&cst=" + URLEncoder.encode(cst, "UTF-8")
                        + "&requestUser=" + URLEncoder.encode(requestUser, "UTF-8")
                        + "&purchaseUser=" + URLEncoder.encode("", "UTF-8")
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


                String urlParameters = "item_name=" + URLEncoder.encode(item_name, "UTF-8")
                        + "&quantity=" + URLEncoder.encode(quant, "UTF-8")
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
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }



    class deleteGrocery extends AsyncTask<Integer, String, String> {
        protected String doInBackground(Integer... params) {
            String response = "";
            try {
                int g_id = params[0];
                String grc_id = Integer.toString(g_id);

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
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }

    //maintenace stuff

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

                    boolean isDone = false;
                    if (isComplete.equals("true")) {
                        isDone = true;
                    }
                    MaintenanceItem maint_item = new MaintenanceItem(mnt_id, desc, causingUser, purchaseUser, Integer.valueOf(groupid), isDone);

                    if(!maint_item.isComplete){
                        mnt_itm.add(maint_item);
                    }
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

    class editMaintenanceItem extends AsyncTask<MaintenanceItem, String, String> {
        protected String doInBackground(MaintenanceItem... params) {
            String response = "";
            try {
                MaintenanceItem mntce_obj = params[0];
                int id = mntce_obj.MaintenanceItemId;
                String item_id = Integer.toString(id);
                String desc = mntce_obj.desc;
                String causingUser = mntce_obj.causingUser;
                String purchaseUser = mntce_obj.purchaseUser;
                Boolean isComplete = mntce_obj.isComplete;
                String isComplet = Boolean.toString(isComplete);

                String urlParameters = "desc=" + URLEncoder.encode(desc, "UTF-8")
                        + "&causingUser=" + URLEncoder.encode(causingUser, "UTF-8")
                        + "&purchaseuser=" + URLEncoder.encode(purchaseUser, "UTF-8")
                        + "&isComplete=" + URLEncoder.encode(isComplet, "UTF-8")
                        + "&id=" + URLEncoder.encode(item_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/edit_maintenance_item.php");
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

    class deleteMaintenanceItem extends AsyncTask<Integer, String, String> {
        protected String doInBackground(Integer... params) {
            String response = "";
            try {
                int mntc_id = params[0];
                String mc_id = Integer.toString(mntc_id);
                String urlParameters = "mntc_id=" + URLEncoder.encode(mc_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/delete_maintenance_item.php");
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

    //Bill stuff

    class getBillList extends AsyncTask<String, String, String> {
       BillList billlist = new BillList();
        private AsyncResponse delegate;
        public getBillList(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";

            try {
                String groupid = params[0];

                String urlParameters = "groupid=" + URLEncoder.encode(groupid, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_bill_list.php");
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
                    String bill_creator = json_obj.get("bill_creator").toString();
                    String assigned_to = json_obj.get("assigned_to").toString();
                    String desc = json_obj.get("desc").toString();
                    String amount = json_obj.get("amount").toString();
                    String groupid = json_obj.get("group_id").toString();
                    int g_id = Integer.valueOf(groupid);
                    int b_id = Integer.valueOf(id);
                    Double total_amount = Double.valueOf(amount);

                    Bill bill = new Bill(b_id, desc, total_amount, bill_creator, assigned_to, g_id);
                    billlist.addBill(bill);
                }

                delegate.processFinish(billlist);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





    class addBill extends AsyncTask<Bill, String, String> {
        protected String doInBackground(Bill... params) {
            String response = "";
            try {
                Bill bill_obj = params[0];
                String desc = bill_obj.desc;
                String bill_creator = bill_obj.userToBill;
                String assigned_to = bill_obj.userToPay;
                Double amount = bill_obj.totalAmount;
                int groupid = bill_obj.groupid;
                String g_id = Integer.toString(groupid);
                String total_amount = Double.toString(amount);

                String urlParameters = "desc=" + URLEncoder.encode(desc, "UTF-8")
                        + "&bill_creator=" + URLEncoder.encode(bill_creator, "UTF-8")
                        + "&assigned_to=" + URLEncoder.encode(assigned_to, "UTF-8")
                        + "&total_amount=" + URLEncoder.encode(total_amount, "UTF-8")
                        + "&groupid=" + URLEncoder.encode(g_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/add_bill.php");
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

    class editBill extends AsyncTask<Bill, String, String> {
        protected String doInBackground(Bill... params) {
            String response = "";
            try {
                Bill bill_obj = params[0];
                String desc = bill_obj.desc;
                String bill_creator = bill_obj.userToBill;
                String assigned_to = bill_obj.userToPay;
                Double amount = bill_obj.totalAmount;
                int groupid = bill_obj.groupid;
                String g_id = Integer.toString(groupid);
                String total_amount = Double.toString(amount);

                String urlParameters = "desc=" + URLEncoder.encode(desc, "UTF-8")
                        + "&bill_creator=" + URLEncoder.encode(bill_creator, "UTF-8")
                        + "&assigned_to=" + URLEncoder.encode(assigned_to, "UTF-8")
                        + "&total_amount=" + URLEncoder.encode(total_amount, "UTF-8")
                        + "&groupid=" + URLEncoder.encode(g_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/edit_bill.php");
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

    class deleteBill extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {
            String response = "";
            try {
                String bill_id = params[0];

                String urlParameters = "bill_id=" + URLEncoder.encode(bill_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/delete_bill.php");
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




    class addAnnoucement extends AsyncTask<Announcement, String, String> {
        protected String doInBackground(Announcement... params) {
            String response = "";
            try {
                Announcement anncmnt = params[0];
                String content = anncmnt.content;
                String poster = anncmnt.poster;
                int groupid = anncmnt.groupid;
                String g_id = Integer.toString(groupid);

                String urlParameters = "content=" + URLEncoder.encode(content, "UTF-8")
                        + "&poster=" + URLEncoder.encode(poster, "UTF-8")
                        + "&groupid=" + URLEncoder.encode(g_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/post_announcement.php");
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

    class getAllAnnoucements extends AsyncTask<String, String, String> {
        private AsyncResponse delegate;
        public getAllAnnoucements(AsyncResponse resp){
            delegate = resp;
        }
        protected String doInBackground(String... params) {
            String response = "";
            try {
                int groupid = Integer.parseInt(params[0]);
                String g_id = Integer.toString(groupid);

                String urlParameters = "groupid=" + URLEncoder.encode(g_id, "UTF-8");
                URL url = new URL("http://104.236.10.133/get_all_announcements.php");
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
            try {
                JSONArray json = new JSONArray(result);

                    JSONObject json_obj = json.getJSONObject(0);
                    String id = json_obj.get("id").toString();
                    String content = json_obj.get("Message").toString();
                    String posted_by = json_obj.get("usersName").toString();
                    String groupid = json_obj.get("groupId").toString();
                    String date = json_obj.get("DatePosted").toString();
                    int g_id = Integer.valueOf(groupid);
                    int a_id = Integer.valueOf(id);

                   Announcement announce = new Announcement(a_id, content, posted_by, date, g_id);

                  delegate.processFinish(announce);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    class addPoints extends AsyncTask<String, String, String> {
        protected String doInBackground(String... params) {
            String response = "";
            try {
               String first_name = params[0];
                String point_amount = params[1];

                String urlParameters = "username=" + URLEncoder.encode(first_name, "UTF-8")
                        + "&points=" + URLEncoder.encode(point_amount, "UTF-8");
                URL url = new URL("http://104.236.10.133/add_points.php");
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
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}