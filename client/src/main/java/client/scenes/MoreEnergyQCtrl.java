<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.shape.Rectangle?>
<?import javafx.scene.text.Font?>

<AnchorPane prefHeight="400.0" prefWidth="600.0" styleClass="background" stylesheets="@../../Images/moreEnergyQ.css" xmlns="http://javafx.com/javafx/17" xmlns:fx="http://javafx.com/fxml/1" fx:controller="client.scenes.MoreEnergyQCtrl">
    <children>
        <Rectangle fx:id="timerContainer" arcHeight="20.0" arcWidth="20.0" fill="#e5e5e5" height="50.0" layoutX="50.0" layoutY="15.0" stroke="BLACK" strokeType="INSIDE" width="500.0" />
        <Rectangle fx:id="timer" arcHeight="20.0" arcWidth="20.0" fill="DODGERBLUE" height="36.0" layoutX="60.0" layoutY="22.0" stroke="BLACK" strokeType="INSIDE" width="480.0" />
        <Rectangle fx:id="questionContainer" arcHeight="5.0" arcWidth="5.0" fill="#8fd1d9" height="300.0" layoutX="49.0" layoutY="80.0" opacity="0.0" stroke="BLACK" strokeType="INSIDE" width="500.0" />
        <Rectangle fx:id="questionTextContainer" arcHeight="5.0" arcWidth="5.0" fill="#1b418c" height="35.0" layoutX="100.0" layoutY="93.0" stroke="BLACK" strokeType="INSIDE" width="400.0" />
        <Rectangle fx:id="answerAContainer" arcHeight="20.0" arcWidth="20.0" fill="#1b418c" height="120.0" layoutX="239.0" layoutY="200.0" stroke="BLACK" strokeType="INSIDE" width="120.0" />
        <Rectangle fx:id="answerCContainer" arcHeight="20.0" arcWidth="20.0" fill="#1b418c" height="120.0" layoutX="80.0" layoutY="200.0" stroke="BLACK" strokeType="INSIDE" width="120.0" />
        <Rectangle fx:id="answerBContainer" arcHeight="20.0" arcWidth="20.0" fill="#1b418c" height="120.0" layoutX="398.0" layoutY="200.0" stroke="BLACK" strokeType="INSIDE" width="120.0" />
      <TextField fx:id="answerTitleA" layoutX="80.0" layoutY="336.0" prefHeight="25.0" prefWidth="120.0" />
      <TextField fx:id="answerTitleB" layoutX="240.0" layoutY="336.0" prefHeight="25.0" prefWidth="120.0" />
      <TextField fx:id="answerTitleC" layoutX="398.0" layoutY="336.0" prefHeight="25.0" prefWidth="120.0" />
      <TextField fx:id="questionTitle" alignment="CENTER" editable="false" layoutX="99.0" layoutY="93.0" prefHeight="35.0" prefWidth="400.0" promptText="What is more expensive?" styleClass="questionTitle">
         <font>
            <Font size="16.0" />
         </font>
      </TextField>
    </children>
</AnchorPane>
