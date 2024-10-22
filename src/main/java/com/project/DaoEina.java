package com.project;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class DaoEina implements Dao<ObjEina>  {

    private void writeList(ArrayList<ObjEina> llista) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (ObjEina eina : llista) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", eina.getId());
                jsonObject.put("nom", eina.getNom());
                jsonObject.put("any", eina.getAny());
                JSONArray jsonLlanguatges = new JSONArray(eina.getLlenguatges());
                jsonObject.put("llenguatges", jsonLlanguatges);
                jsonArray.put(jsonObject);
            }
            //Revisar
            PrintWriter out = new PrintWriter(Main.einesPath);
            out.write(jsonArray.toString(4)); // 4 es l'espaiat
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getPosition (int id) {
        int result = -1;
        ArrayList<ObjEina> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            ObjEina eina = llista.get(cnt);
            if (eina.getId() == id) {
                result = cnt;
                break;
            }
        }
        return result;
    }

    @Override
    public void add(ObjEina eina) {
        ArrayList<ObjEina> llista = getAll();
        ObjEina item = get(eina.getId());
        if (item == null) {
            llista.add(eina);
            writeList(llista);
        }
    }


    @Override
    public ObjEina get(int id) {
        ObjEina result = null;
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            result = llista.get(pos);
        }
        return result;
    }

    //Revisar
    @Override
    public ArrayList<ObjEina> getAll() {
        ArrayList<ObjEina> result = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(Main.einesPath)));
            
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String nom = jsonObject.getString("nom");
                int any = jsonObject.getInt("any");
                JSONArray jsonLlanguatges = jsonObject.getJSONArray("llenguatges");
                ArrayList<Integer> llenguatges = new ArrayList<>();
                for (int j = 0; j < jsonLlanguatges.length(); j++) {
                    llenguatges.add(jsonLlanguatges.getInt(j));
                }
                ObjEina eina = new ObjEina(id, nom,any, llenguatges);
                result.add(eina);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    

    @Override
    public void update(int id, ObjEina eina) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.set(pos, eina);
            writeList(llista);
        }
    }

    @Override
    public void delete(int id) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.remove(pos);
            writeList(llista);
        }
    }

    public void setLlenguatgesAdd(int einaId, int llenguatgeId) {
        ObjEina eina = get(einaId);
        if (eina != null) {
            ArrayList<Integer> supportedLanguages = eina.getLlenguatges();
            if (!supportedLanguages.contains(llenguatgeId)) {

                supportedLanguages.add(llenguatgeId);
                eina.setLlenguatges(supportedLanguages); 

                update(einaId, eina); 
            }
        }
    }

    public void setLlenguatgesDelete(int einaId, int llenguatgeId) {
        ObjEina eina = get(einaId);
        if (eina != null) {
            ArrayList<Integer> supportedLanguages = eina.getLlenguatges();
            if (supportedLanguages.contains(llenguatgeId)) {

                supportedLanguages.remove(Integer.valueOf(llenguatgeId));
                eina.setLlenguatges(supportedLanguages); 

                update(einaId, eina); 
            } 
        } 
    }

    @Override
    public void setNom(int id, String nom) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.get(pos).setNom(nom);
            writeList(llista);
        }
    }

    @Override
    public void setAny(int id, int any) {
        ArrayList<ObjEina> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.get(pos).setAny(any);
            writeList(llista);
        }
    }

    @Override
    public void print () {
        ArrayList<ObjEina> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            System.out.println("    " + llista.get(cnt));
        }
    }

}
