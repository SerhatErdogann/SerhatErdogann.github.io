<!DOCTYPE html>
<html lang="en">
<head>
    <title>Currency Calculation</title>
    <meta name="description" content="CENG 311 Inclass Activity 5" />
</head>
<body>
<?php
$rates = [
    'FUSD' => ['TUSD' => 1, 'TCAD' => 1.43, 'TEUR' => 0.92],
    'FCAD' => ['TCAD' => 1, 'TUSD' => 0.70, 'TEUR' => 0.65],
    'FEUR' => ['TEUR' => 1, 'TUSD' => 1.08, 'TCAD' => 1.54],
];

$result = "0.00"; 

if (isset($_GET['from_value'], $_GET['from_currency'], $_GET['to_currency'])) {
    $from_value = (float)$_GET['from_value'];
    $from_currency = $_GET['from_currency'];
    $to_currency = $_GET['to_currency'];

    $result = number_format($from_value * $rates[$from_currency][$to_currency], 2);
}
?>

<form action="activity5.php" method="GET">
    <table>
        <tr>
            <td>
				From:
			</td>
            <td>
                <input type="text" name="from_value" />
            </td>
            <td>
				Currency:
			</td>
            <td>
                <select name="from_currency">
                    <option value="FUSD">USD</option>
                    <option value="FCAD">CAD</option>
                    <option value="FEUR">EUR</option>
                </select>
            </td>    
        </tr>
        <tr>
            <td>
				To:
			</td>
            <td>
                <input type="text" value="<?php echo $result; ?>" readonly />
            </td>
            <td>
				Currency:
			</td>
            <td>
                <select name="to_currency">
                    <option value="TUSD">USD</option>
                    <option value="TCAD">CAD</option>
                    <option value="TEUR">EUR</option>
                </select>
            </td>    
        </tr>
        <tr>
            <td></td><td></td><td></td>
            <td>
                <input type="submit" value="convert"/>
            </td>    
        </tr>
    </table>
</form>
</body>
</html>
