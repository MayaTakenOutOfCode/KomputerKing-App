package com.maya.kliksoftapp1;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GeneratorProduct{
    private Context context;
    public GeneratorProduct(Context context) {
        this.context = context;
    }

    public void CreateProducts(View view){

        GridLayout gridLayoutContainer = ((AppCompatActivity) context).findViewById(R.id.products);
        Product product1 = new Product("Komputer 4k rtx 4024", "Dobry komputer do gier uwu", 500, 0);
        Product product2 = new Product("laptop 2k rtx 404", "Dobry laptop uwu", 300, 1);
        Product product3 = new Product("telefon HD intelcore 2", "Dobry telefon", 300, 2);
        Product product4 = new Product("tablet 3k gtx 1090px", "Dobry tablet", 500, 3);
        Product product5 = new Product("telewizor 12k LG", "Tv 12k firmy lg", 5000, 4);


        ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);



        for (int i = 0; i < products.size(); i++) {
            Product product = getProductById(products, i);
            int kalk = i*4;


            // GENERATOR obrazu produktu
            String imageName = "zdjecie" + i;
            ImageView productImage = new ImageView(context);

            int imageResource = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
            productImage.setImageResource(imageResource);

            // Parametry CSS dla ImageView
            GridLayout.LayoutParams paramsImage = new GridLayout.LayoutParams();
            paramsImage.width = 450; // Szerokość w pikselach
            paramsImage.height = 450; // Wysokość


            paramsImage.rowSpec = GridLayout.spec(kalk, 4); // obrazek w różnych wierszach (kalk mnożymy przez 4, aby zrobić miejsce na inne elementy)
            paramsImage.columnSpec = GridLayout.spec(0); // Kolumna 0 (lewa strona)
            paramsImage.setMargins(0, 0, 0, 5); // Margines dolny 5px
            productImage.setLayoutParams(paramsImage);
            gridLayoutContainer.addView(productImage);

            // GENERATOR nazwy produktu
            TextView nameText = new TextView(context);
            nameText.setText(product.getProductName());
            nameText.setMaxWidth(550);
            nameText.setTextAppearance(R.style.productListName);
            GridLayout.LayoutParams paramsText1 = new GridLayout.LayoutParams();
            paramsText1.rowSpec = GridLayout.spec(kalk); // Nazwa w tym samym wierszu co obrazek
            paramsText1.columnSpec = GridLayout.spec(1); // Kolumna 1 (prawa strona)

            nameText.setLayoutParams(paramsText1);
            gridLayoutContainer.addView(nameText);

            // GENERATOR ceny produktu
            TextView priceText = new TextView(context);
            priceText.setText(product.getProductPrice() + " zł");
            priceText.setMaxWidth(550);
            priceText.setTextColor(Color.WHITE); // Kolor tekstu na biały
            GridLayout.LayoutParams paramsPrice = new GridLayout.LayoutParams();
            paramsPrice.rowSpec = GridLayout.spec(kalk+1); // Cena poniżej obrazu
            paramsPrice.columnSpec = GridLayout.spec(1); // Kolumna 1 (prawa strona)
            priceText.setLayoutParams(paramsPrice);
            gridLayoutContainer.addView(priceText);

            // GENERATOR opisu produktu
            TextView descriptionView = new TextView(context);
            descriptionView.setText(product.getProductDesc());
            descriptionView.setMaxWidth(500);
            descriptionView.setTextAppearance(R.style.productListDescription);
            GridLayout.LayoutParams paramsText3 = new GridLayout.LayoutParams();
            paramsText3.rowSpec = GridLayout.spec(kalk+2); // Opis poniżej ceny
            paramsText3.columnSpec = GridLayout.spec(1); // Kolumna 1 (prawa strona)
            descriptionView.setLayoutParams(paramsText3);
            gridLayoutContainer.addView(descriptionView);

            // Generowanie przycisku dla produktu
            Button productButton = new Button(context);
            productButton.setText("Zamów teraz");

            // Parametry przycisku
            GridLayout.LayoutParams paramsButton = new GridLayout.LayoutParams();
            paramsButton.rowSpec = GridLayout.spec(kalk+3); // Przycisk w nowym wierszu
            paramsButton.columnSpec = GridLayout.spec(1); // Kolumna 1 (prawa strona)
            productButton.setLayoutParams(paramsButton);
            gridLayoutContainer.addView(productButton);


        }
    }
    public static Product getProductById(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;  // Jeśli nie znaleziono
    }
}
