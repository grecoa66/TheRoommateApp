package rommateapp.development.albie.therommateapp;

/**
 * Created by Matthew on 10/20/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.os.AsyncTask;

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


public class HTTP_Connector extends Activity  {

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
        int chore_list_id;
        private userGroupResponse delegate;
        private User user;
        ArrayList<Chore> chores = new ArrayList<>();

        public getUser(userGroupResponse resp){
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
            user = new User(Integer.parseInt(useid) ,firstname, lastname, emailaddr, phonenum);
            delegate.userFinish(user);
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
        private choreListResponse delegate;
        ArrayList<Chore> chores = new ArrayList<>();

        public getChoreList(choreListResponse resp){
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

        @Override
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
            //    Toast.makeText(ctx, chores.toString(), Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            delegate.choresListFinish(chores);
            return;
        }

        protected void onPreExecute(String result) {
            // something...
        }

        public ArrayList<Chore> createChoreListObject() {
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

              //  Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
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
}