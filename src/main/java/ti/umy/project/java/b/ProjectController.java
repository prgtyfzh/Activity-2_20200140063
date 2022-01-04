/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ti.umy.project.java.b;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pragitya Faazha
 */
@Controller
public class ProjectController {
    
    @RequestMapping("/inputsayur")
    public String setInput(HttpServletRequest data, Model model){
        
        String sayur = data.getParameter("var_sayur");
        model.addAttribute("sayur", sayur);
        
        String harga = data.getParameter("var_harga");
        Double hargakg = Double.valueOf(harga);
        model.addAttribute("harga", harga);
        
        String jumlah = data.getParameter("var_jumlah");
        Double jumlahbl = Double.valueOf(jumlah);
        model.addAttribute("jumlah", jumlah);
        
        String tunai = data.getParameter("var_tunai");
        Double uangbyr = Double.valueOf(tunai);
        model.addAttribute("tunai", tunai);
               
        Double jumlahbayar = jumlahbl * hargakg;
        Double subtotal = jumlahbl * hargakg;
        Double total = null;
        int diskon = 0;
        Double hargadiskon;
               
        if(jumlahbayar < 16000){
            diskon = 0;
            total = jumlahbayar - (jumlahbayar * 0/100);
            hargadiskon = jumlahbayar*diskon/100;
        }else if (jumlahbayar < 25000){
            diskon = 10;
            total = jumlahbayar - (jumlahbayar * 10/100);
            hargadiskon = jumlahbayar*diskon/100;
        }else {
            diskon = 15;
            total = jumlahbayar - (jumlahbayar * 15/100);
            hargadiskon = jumlahbayar*diskon/100;
        }
             
        model.addAttribute("diskon", diskon);
        model.addAttribute("hargadiskon", hargadiskon);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", total);
        
        Double kembali = uangbyr - total;
        Double uangkurang = total - uangbyr;
        String pesan = null;
        
        if(uangbyr > total) 
        {
            pesan = "Kembali " + kembali;
        }
        else if (uangbyr < total)
        {
            pesan = "Uang Anda Kurang " + uangkurang;
        }
        else
        {
            pesan = "Uang Anda Pas";
        }
        
        model.addAttribute("pesan", pesan);
        
        return "view";
    }
}