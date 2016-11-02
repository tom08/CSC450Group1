package com.CSC450.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.CSC450.ars.domain.Page;
import com.CSC450.dao.impl.PageDao;
import com.CSC450.ars.domain.AdLocationVisit;
import com.CSC450.dao.impl.AdLocationVisitDao;
import com.CSC450.ars.domain.Keyword;
import com.CSC450.dao.impl.KeywordDao;


public class UpdateClient {
    private String address;
    private BufferedReader in;
    private PrintWriter out;
	private PageDao pageDao = new PageDao();
	private AdLocationVisitDao adLocationVisitDao = new AdLocationVisitDao();
	private KeywordDao keywordDao = new KeywordDao();

    public UpdateClient(){
        address = "127.0.0.1";
    }

    private void saveKeyword(String[] data){
        if(keywordDao.getById(Long.parseLong(data[0])) == null){
            Keyword kwd = new Keyword(Long.parseLong(data[0]), data[1]);
            keywordDao.save(kwd);
        }
    }

    private void savePage(String[] data){
        if(pageDao.getById(Long.parseLong(data[0])) == null){
            Page page = new Page(Long.parseLong(data[0]), data[1]);
            pageDao.save(page);
        }
    }

    private void saveAdLocationVisit(String[] data){
        if(adLocationVisitDao.getById(Long.parseLong(data[0])) == null){
            AdLocationVisit visit = new AdLocationVisit(
                    Long.parseLong(data[0]),
                    Double.parseDouble(data[2]),
                    Double.parseDouble(data[3]),
                    Double.parseDouble(data[4]),
                    Long.parseLong(data[5]),
                    data[1]
                    );
            adLocationVisitDao.save(visit);
        }
    }

    private void saveRelationship(String[] data){
        long page_id = Long.parseLong(data[1]);
        long keyword_id = Long.parseLong(data[0]);
        Page page = pageDao.getById(page_id);
        Keyword keyword = keywordDao.getById(keyword_id);
        List<Keyword> keywords = page.getKeywords();
        boolean exists = false;
        if(keywords != null && !keywords.isEmpty()){
            for(int i = 0; i < keywords.size(); i++){
                if(keywords.get(i).getId() == keyword_id)
                    exists = true;
            }
        }
        if(!exists && keyword != null && page != null){
            page.addKeyword(keyword);
            pageDao.save(page);
        }
    }

    public void connectToServer() throws IOException {

        Socket socket = new Socket(address, 12000);
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("UPDATE");
        String msg = in.readLine();
        String type = "";
        String[] data = msg.split(",,");
        if(data[0].equals("TYPE"))
            type = data[1];
        while(msg != null) {
            msg = in.readLine();
            if(msg == null)
                break;
            data = msg.split(",,");
            if(data[0].equals("TYPE")){
                type = data[1];
                continue;
            }
            if(type.equals("PAGE")){
                savePage(data);
            }
            else if(type.equals("AD")){
                saveAdLocationVisit(data);
            }
            else if(type.equals("KEY")){
                saveKeyword(data);
            }
            else if(type.equals("KPR")){
                saveRelationship(data);
            }
        }
    }
}