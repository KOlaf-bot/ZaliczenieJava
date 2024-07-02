package com.example.bmi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BmiController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateBmi(@RequestParam("weight") double weight,
                               @RequestParam("height") double height,
                               Model model) {
        String result = calculateBmi(weight, height);
        model.addAttribute("result", result);
        return "index";
    }

    private String calculateBmi(double weight, double height) {
        if (weight > 0 && height > 0) {
            double bmi = weight / (height * height);
            String result = "Twoje BMI to " + String.format("%.2f", bmi);

            if (bmi < 18.5) {
                result += " - Niedowaga";
            } else if (bmi >= 18.5 && bmi < 25) {
                result += " - Normalna waga";
            } else if (bmi >= 25 && bmi < 30) {
                result += " - Nadwaga";
            } else {
                result += " - Otyłość";
            }

            return result;
        } else {
            return "Wprowadź prawidłowe wartości";
        }
    }
}
