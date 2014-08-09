package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MainController {

	private static final NumberFormat currency = NumberFormat
			.getCurrencyInstance();
	private static final NumberFormat percent = NumberFormat
			.getPercentInstance();

	private BigDecimal tipPercentage = new BigDecimal(.15);

	@FXML
	private TextField AmountField;

	@FXML
	private Label Percent;

	@FXML
	private Slider PercentSlider;

	@FXML
	private TextField TipField;

	@FXML
	private TextField TotalField;

	@FXML
	private void calcButtonPressed(ActionEvent event) {
		try {
			BigDecimal amount = new BigDecimal(AmountField.getText());
			BigDecimal tip = amount.multiply(tipPercentage);
			BigDecimal total = amount.add(tip);

			TipField.setText(currency.format(tip));
			TotalField.setText(currency.format(total));
		} catch (NumberFormatException ex) {
			AmountField.setText("Enter Amount");
			AmountField.selectAll();
			AmountField.requestFocus();
		}
	}

	public void initialize() {
		currency.setRoundingMode(RoundingMode.HALF_UP);
		
		PercentSlider.valueProperty().addListener(
				new ChangeListener<Number>()
				{
					@Override
					public void changed(ObservableValue<? extends Number> ov,
						Number oldValue, Number newValue)
							{
							tipPercentage =
								BigDecimal.valueOf(newValue.intValue() / 100.0);
							Percent.setText(percent.format(tipPercentage));
										
							}
				}
				);
	}
}
