import React, { useState } from 'react';
import { Button } from '@material-ui/core'

const MonthSelector = () => {
  const [summerStart, setSummerStart] = useState(localStorage.getItem('summerStart') ? localStorage.getItem('summerStart') : 5);
  const [summerEnd, setSummerEnd] = useState(localStorage.getItem('summerEnd') ? localStorage.getItem('summerEnd') : 9);
  const [winterStart, setWinterStart] = useState(localStorage.getItem('winterStart') ? localStorage.getItem('winterStart') : 12);
  const [winterEnd, setWinterEnd] = useState(localStorage.getItem('winterEnd') ? localStorage.getItem('winterEnd') : 2);

  const [summerStartEdited, setSummerStartEdited] = useState(localStorage.getItem('summerStart') ? localStorage.getItem('summerStart') : 5);
  const [summerEndEdited, setSummerEndEdited] = useState(localStorage.getItem('summerEnd') ? localStorage.getItem('summerEnd') : 9);
  const [winterStartEdited, setWinterStartEdited] = useState(localStorage.getItem('winterStart') ? localStorage.getItem('winterStart') : 12);
  const [winterEndEdited, setWinterEndEdited] = useState(localStorage.getItem('winterEnd') ? localStorage.getItem('winterEnd') : 2);;

  const handleMonthRangeChange = () => {
    if (doesOverlap(summerStartEdited, summerEndEdited, winterStartEdited, winterEndEdited) || doesOverlap(winterStartEdited, winterEndEdited, summerStartEdited, summerEndEdited)) {
      setSummerStartEdited(summerStart)
      setSummerEndEdited(summerEnd)
      setWinterStartEdited(winterStart)
      setWinterEndEdited(winterEnd)
      alert('Ranges cannot overlap');
      return;
    }

    localStorage.setItem('summerStart', parseInt(summerStartEdited));
    localStorage.setItem('summerEnd', parseInt(summerEndEdited));
    localStorage.setItem('winterStart', parseInt(winterStartEdited));
    localStorage.setItem('winterEnd', parseInt(winterEndEdited));
    setSummerStart(summerStartEdited)
    setSummerEnd(summerEndEdited)
    setWinterStart(winterStartEdited)
    setWinterEnd(winterEndEdited)
    alert('Updated ranges');
  }

  const doesOverlap = (firstStart, firstEnd, secondStart, secondEnd) => {
    if (secondStart > secondEnd) {
      firstStart += (firstStart > firstEnd) ? 0 : 12;
      firstEnd = parseInt(firstEnd) + 12;
      secondEnd = parseInt(secondEnd) + 12;
    }
    console.log(firstStart, firstEnd, secondStart, secondEnd)
    return (firstStart >= secondStart && firstStart <= secondEnd) || (firstEnd >= secondStart && firstEnd <= secondEnd)
  }

  return (
    <div>
      <div>
        Summer months:
        <input type="number" min="1" max="12" step="1" name="summerStart" value={summerStartEdited} onChange={(e) => setSummerStartEdited(e.target.value)} />
        to
        <input type="number" min="1" max="12" step="1" name="summerEnd" value={summerEndEdited} onChange={(e) => setSummerEndEdited(e.target.value)} />
      </div>
      <label>
        Winter months:
        <input type="number" min="1" max="12" step="1" name="winterStart" value={winterStartEdited} onChange={(e) => setWinterStartEdited(e.target.value)} />
        to
        <input type="number" min="1" max="12" step="1" name="winterEnd" value={winterEndEdited} onChange={(e) => setWinterEndEdited(e.target.value)} />
      </label>
      <br/>
      <Button onClick={handleMonthRangeChange}>Submit</Button>
    </div>
  );
};

export default MonthSelector;