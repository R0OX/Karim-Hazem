<?php
$currentTime = strtotime('17:00:00');
$startTime = strtotime('07:00:00');

$remainingTime = $currentTime - $startTime;

$deductionCount = 0;

// Check if user arrived early (negative remaining time)
if ($remainingTime < 0) {
  
  $deductionCount = 0; 
} else {
  while (strtotime($remainingTime) > strtotime('00:00:00')) {
    if (strtotime($remainingTime) > strtotime('03:00:00')) {
      $deductionCount+=1;
      $remainingTime -= strtotime('07:00:00');
    } else {
      $deductionCount += 0.5;
      $remainingTime -= strtotime('02:00:00');
    }
  }
}

echo $deductionCount;
?>