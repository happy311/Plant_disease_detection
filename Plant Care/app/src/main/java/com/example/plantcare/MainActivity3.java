package com.example.plantcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity3 extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        String label = getIntent().getStringExtra("label");
        int confidence = getIntent().getIntExtra("confidence",-99);
        String imagepath ;
        String disease ;
        Bitmap bitmap;
        ImageView img;
        String medicine ;
        String helpingtext ;
        TextView dis,med,help;
        dis = findViewById(R.id.resultdec);
        med = findViewById(R.id.resultmed);
        help = findViewById(R.id.result);
        img = findViewById(R.id.resultimg);
        switch (label){
            case "Apple__Apple_scab":
                disease = "आपके सेब के पेड़ में पपड़ी की बीमारी है " + confidence +"% के अनुमान के साथ";
                medicine = "Mancozeb(मैनकोज़ेब)";
                helpingtext="वर्षा ऋतु में पूरे वृक्ष बेसिन को पेड़ के तने से 30 सेमी की दूरी पर मैनकोजेब(Mancozeb) (0.3%) से डुबोएं। पपड़ी प्रवण क्षेत्रों में प्राथमिक पपड़ी चरण तक स्प्रे के बीच 12-14 दिनों का अंतराल बनाए रखा जाना चाहिए। कम से कम 15 दिनों के लिए तेल स्प्रे से पहले और बाद में कैप्टान(captan) के उपयोग से बचना चाहिए। डोडाइन को कठोर पानी के साथ नहीं मिलाया जाना चाहिए।";
                imagepath = "Mancozeb.jpg";
                break;
            case "Apple__Black_rot":
                disease = "आपके सेब के पेड़ में सेब की काली सड़न की बीमारी है " + confidence +"% के अनुमान के साथ";
                medicine = "Boscalid(बोस्कलिड)";
                helpingtext="विशेषज्ञों द्वारा अनुशंसित या कवकनाशी लेबल निर्देशों के आधार पर एक नियमित स्प्रे शेड्यूल का पालन करें। बढ़ते मौसम के दौरान सुरक्षा बनाए रखने के लिए निर्दिष्ट अंतराल पर कवकनाशी को फिर से लागू करें।";
                imagepath = "Boscalid.jpg";
                break;
            case "Apple__Cedar_apple_rust":
                disease = "आपके सेब के पेड़ को देवदार सेब जंग की बीमारी है "+ confidence +"% के अनुमान के साथ";
                medicine = "Myclobutanil(माइक्लोबुटानिल)";
                helpingtext="अपघटन को तेज करने के लिए गिरी हुई पत्तियों पर 5% यूरिया का घोल लगाएं, जो ओवरविन्टरिंग कवक को कम करता है। गिरे हुए फलों को उठाकर फेंक दें। 1MPFS = बहुउद्देशीय फल स्प्रे। 2 माइक्लोबुटानिल (इम्यूनोक्स®) को प्रति सीजन में 10 बार से अधिक न लगाएं।";
                imagepath = "Myclobutanil.jpg";
                break;
            case "Apple__healthy":
                disease = "आपका सेब का पेड़ स्वस्थ है "+ confidence +"% के अनुमान के साथ";
                medicine = "fertilizers(उर्वरक)";
                helpingtext="विकास को बढ़ाने के लिए पूर्ण एनपीके उर्वरकों का उपयोग किया जा सकता है";
                imagepath = "fertilizers.jpg";
                break;
            case "Peach__Bacterial_spot":
                disease = "आपके आड़ू के पेड़ को बैक्टीरियल स्पॉट रोग है "+ confidence +"% के अनुमान के साथ";
                medicine = "Streptomycin(स्ट्रेप्टोमाइसिन)";
                helpingtext="स्ट्रेप्टोमाइसिन एक उत्कृष्ट फायर ब्लाइट सामग्री है, जो बारिश की घटनाओं से पहले दो से चार दिनों के लिए आगे नियंत्रण प्रदान करता है और बारिश की घटना के बाद 12-24 घंटों के भीतर लागू होने पर ब्लॉसम ब्लाइट नियंत्रण के लिए प्रभावी होगा। स्ट्रेप्टोमाइसिन का उपयोग 24 औंस प्रति एकड़ की दर से किया जाता है और इसे गैर-आयनिक सर्फेक्टेंट जैसे रेगुलैड (1 पिंट प्रति 100 गैलन) के साथ लागू किया जाना चाहिए। सर्फेक्टेंट का उपयोग फूलों पर एंटीबायोटिक के जमाव को बढ़ाता है और संभावना बढ़ जाती है कि महत्वपूर्ण कलंक लक्ष्य हिट हो जाएंगे। ";
                imagepath = "Streptomycin.jpg";
                break;
            case "Peach_healthy":
                disease = "आपका आड़ू का पेड़ स्वस्थ है "+ confidence +"% के अनुमान के साथ";
                medicine = "fertilizers(उर्वरक)";
                helpingtext="विकास को बढ़ाने के लिए पूर्ण एनपीके उर्वरकों का उपयोग किया जा सकता है";
                imagepath = "fertilizers.jpg";
                break;
            case "Pepper_bell_Bacterial_spot":
                disease = "आपके काली मिर्च के पेड़ को बैक्टीरियल स्पॉट रोग है "+ confidence +"% के अनुमान के साथ";
                medicine = "Streptomycin(स्ट्रेप्टोमाइसिन)";
                helpingtext="स्ट्रेप्टोमाइसिन एक उत्कृष्ट फायर ब्लाइट सामग्री है, जो बारिश की घटनाओं से पहले दो से चार दिनों के लिए आगे नियंत्रण प्रदान करता है और बारिश की घटना के बाद 12-24 घंटों के भीतर लागू होने पर ब्लॉसम ब्लाइट नियंत्रण के लिए प्रभावी होगा। स्ट्रेप्टोमाइसिन का उपयोग 24 औंस प्रति एकड़ की दर से किया जाता है और इसे गैर-आयनिक सर्फेक्टेंट जैसे रेगुलैड (1 पिंट प्रति 100 गैलन) के साथ लागू किया जाना चाहिए। सर्फेक्टेंट का उपयोग फूलों पर एंटीबायोटिक के जमाव को बढ़ाता है और संभावना बढ़ जाती है कि महत्वपूर्ण कलंक लक्ष्य हिट हो जाएंगे। ";
                imagepath = "Streptomycin.jpg";
                break;
            case "Pepper_bell_healthy":
                disease = "आपका काली मिर्च का पेड़ स्वस्थ है "+ confidence +"% के अनुमान के साथ";
                medicine = "fertilizers(उर्वरक)";
                helpingtext="विकास को बढ़ाने के लिए पूर्ण एनपीके उर्वरकों का उपयोग किया जा सकता है";
                imagepath = "fertilizers.jpg";
                break;
            case "Strawberry__Lead_scorch":
                disease = "आपके स्ट्रॉबेरी के पेड़ को सीसा झुलसा रोग है "+ confidence +"% के अनुमान के साथ";
                medicine = "Streptomycin(स्ट्रेप्टोमाइसिन)";
                helpingtext="स्ट्रेप्टोमाइसिन एक उत्कृष्ट फायर ब्लाइट सामग्री है, जो बारिश की घटनाओं से पहले दो से चार दिनों के लिए आगे नियंत्रण प्रदान करता है और बारिश की घटना के बाद 12-24 घंटों के भीतर लागू होने पर ब्लॉसम ब्लाइट नियंत्रण के लिए प्रभावी होगा। स्ट्रेप्टोमाइसिन का उपयोग 24 औंस प्रति एकड़ की दर से किया जाता है और इसे गैर-आयनिक सर्फेक्टेंट जैसे रेगुलैड (1 पिंट प्रति 100 गैलन) के साथ लागू किया जाना चाहिए। सर्फेक्टेंट का उपयोग फूलों पर एंटीबायोटिक के जमाव को बढ़ाता है और संभावना बढ़ जाती है कि महत्वपूर्ण कलंक लक्ष्य हिट हो जाएंगे। ";
                imagepath = "Streptomycin.jpg";
                break;
            case "Strawberry__healthy":
                disease = "आपका स्ट्रॉबेरी का पेड़ स्वस्थ है "+ confidence +"% के अनुमान के साथ";
                medicine = "fertilizers(उर्वरक)";
                helpingtext="विकास को बढ़ाने के लिए पूर्ण एनपीके उर्वरकों का उपयोग किया जा सकता है";
                imagepath = "fertilizers.jpg";
                break;
            case "Potato___Early_blight":
                disease = "आपके आलू के पौधे को अर्ली ब्लाइट रोग है "+ confidence +"% के अनुमान के साथ";
                medicine = "Chlorothalonil(क्लोरोथेलोनिल)";
                helpingtext=" 0.10-3.75 द्रव औंस प्रति सौ वजन (cwt) की दर से लागू करें। यह ब्लैक स्कर्फ, स्टेम कैंकर और बीज-जनित ब्लैक डॉट को दबाने में मदद करता है, साथ ही सिल्वर स्कर्फ से बचाता है। प्रत्येक उत्पाद के लिए अनुशंसित खुराक का पालन करें। रोपण से पहले या रोपण के दौरान क्लोरोथेलोनिल लागू करें। फसल के 7 दिनों के भीतर लागू न करें।  कवकनाशी प्रतिरोध से अवगत रहें और सर्वोत्तम प्रथाओं का पालन करें।";
                imagepath = "Chlorothalonil.jpg";
                break;
            case "Potato___Late_blight":
                disease = "आपके आलू के पौधे को लेट ब्लाइट रोग है "+ confidence +"% के अनुमान के साथ";
                medicine = "Metalaxyl-M(मेटालैक्सिल-एम)";
                helpingtext="निर्माता के निर्देशों के अनुसार मेटालैक्सिल-एम कवकनाशी को पतला करें। आलू के पौधों के पत्ते पर समान रूप से स्प्रे करें, पत्तियों की ऊपरी और निचली दोनों सतहों को कवर करें। पौधे की वृद्धि के शुरुआती चरणों के दौरान या जब रोग के लक्षण पहली बार देखे जाते हैं, तो लागू करें। रोग के दबाव की गंभीरता के आधार पर अनुशंसित अंतराल पर दोहराएं।";
                imagepath = "Metalaxyl-M.jpg";
                break;
            case "Potato___healthy":
                disease = "आपका आलू का पौधा स्वस्थ है "+ confidence +"% के अनुमान के साथ";
                medicine = "fertilizers(उर्वरक)";
                helpingtext="विकास को बढ़ाने के लिए पूर्ण एनपीके उर्वरकों का उपयोग किया जा सकता है";
                imagepath = "fertilizers.jpg";
                break;
            case "Cherry_(including_sour)___Powdery_mildew":
                disease = "आपके चेरी के पौधे में ख़स्ता फफूंदी रोग है "+ confidence +"% के अनुमान के साथ";
                medicine = "Flutriafol(फ्लुट्रियाफोल)";
                helpingtext="बढ़ते मौसम के दौरान फ्लुट्रियाफोल लागू करें जब चेरी का पेड़ सक्रिय रूप से पत्तियों और फलों का उत्पादन कर रहा होता है। चरम मौसम की स्थिति (जैसे उच्च तापमान या भारी बारिश) के दौरान आवेदन करने से बचें।  रोग के लक्षण दिखाई देने से पहले Flutriafol लागू करें। यह पेड़ को फंगल रोगों से बचाने में मदद करता है।";
                imagepath = "Flutriafol.jpg";
                break;
            case "Cherry_(including_sour)___healthy":
                disease = "आपका चेरी का पौधा स्वस्थ है "+ confidence +"% के अनुमान के साथ";
                medicine = "fertilizers(उर्वरक)";
                helpingtext="विकास को बढ़ाने के लिए पूर्ण एनपीके उर्वरकों का उपयोग किया जा सकता है";
                imagepath = "fertilizers.jpg";
                break;
            default:
                disease = "a";
                medicine = "a";
                helpingtext="a";
                imagepath = "a";
                break;
        }
        dis.setText(disease);
        med.setText(medicine);
        help.setText(helpingtext);
        try {
            // Open the image file from assets
            InputStream inputStream = MainActivity3.this.getAssets().open(imagepath);

            // Decode the input stream into a bitmap
            bitmap = BitmapFactory.decodeStream(inputStream);
            // Close the input stream
            inputStream.close();
            bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            img.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log or display an error message)
        }

    }
}