/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alamnurcahaya
 */

@Controller
public class SayurProcessor {
    
    @RequestMapping ("/Alam")
    public String inputSayur (HttpServletRequest data, Model get)
    {
        String inputSayur = data.getParameter("namasayur"); //name of vegetables
        String inputHarga = data.getParameter("hargaperkilo"); //price per kilo
        String inputJumlahBeli = data.getParameter("kuantitas"); //quantity purchased
        
        Integer hargaSayur = Integer.valueOf(inputHarga);
        Double jumlahBeli = Double.valueOf(inputJumlahBeli);
        Double jumlahBayarAwal = hargaSayur * jumlahBeli; //initial payment amount
        
        Integer persenDiskon = null; //discount amount (%)
        Double hargaDiskon;
        String keterangan = "";
        
        if (jumlahBayarAwal <= 16000)
        {
            persenDiskon = 0;
        }
        else if (jumlahBayarAwal > 16000 && jumlahBayarAwal < 25000)
        {
           persenDiskon = 10;
        }
        else if (jumlahBayarAwal > 25000)
        {
            persenDiskon = 15;
        }
       
        
        hargaDiskon = jumlahBayarAwal*persenDiskon/100; //total discounted price (Rp)
        
        Double totalBayar = jumlahBayarAwal - hargaDiskon; //total price to be paid

        String inputBayar = data.getParameter("nominalbayar");
        Integer nominalBayar = Integer.valueOf(inputBayar); //input to be paid
        
        
        
        Double kembali =  nominalBayar - totalBayar;
        Double kurang = totalBayar - nominalBayar;
        
        if (totalBayar < nominalBayar)
        {
            keterangan = "Kembalian anda Rp. " + kembali;
        }
        
        else if (totalBayar > nominalBayar)
        {
            keterangan = "Uang anda kurang Rp. " + kurang;
        }
        
        else
        {
            keterangan = "Uang anda pas";
        }
        
        get.addAttribute("varsayur", inputSayur);
        get.addAttribute("varharga", inputHarga);
        get.addAttribute("varjumlah", inputJumlahBeli);
        get.addAttribute("totalawal", jumlahBayarAwal);
        get.addAttribute("discountpercentage", persenDiskon);
        get.addAttribute("discountprice", hargaDiskon);
        get.addAttribute("vartotal", totalBayar);
        get.addAttribute("bayar", inputBayar);
        get.addAttribute("ket", keterangan);
        
        return "Alam";
    }
}

