# Causality Reverser
TLDR: This is a toy project to verify a popular neuroscience book’s claims re: human perception of time and causality.

## Inspiration
In Chapter 2 of David Eagleman’s [*Incognito: The Secret Lives of the Brain*](https://en.wikipedia.org/wiki/Incognito:_The_Secret_Lives_of_the_Brain), I came across this specific passage arguing that our perception of time is a mental construction:
> As another example of the strangeness of time, consider how you know when you performed an action and when you sensed the consequences. If you were an engineer, you would reasonably suppose that something you do at timepoint 1 would result in sensory feedback at timepoint 2. So you would be surprised to discover that in the lab we can make it seem to you as though 2 happens before 1. Imagine that you can trigger a flash of light by pressing a button. Now imagine that we inject a slight delay—say, a tenth of a second—between your press and the consequent flash. After you’ve pressed the button several times, your brain adapts to this delay, so that the two events seem slightly closer in time. Once you are adapted to the delay, we surprise you by presenting the flash immediately after you press the button. In this condition, you will believe the flash happened before your action: you experience an illusory reversal of action and sensation. 

Rather than perceive sensory signals “directly” (whatever that means), our brains try to reconstruct the sensory signals we receive in a way that “makes sense”. One way our brains do so is to calibrate our perception so that that incoming signals from our interaction with the world appears to be simultaneous. In the author’s words: 

> The best way to calibrate timing expectations of incoming signals is to interact with the world: each time a person kicks or knocks on something, the brain can make the assumption that the sound, sight, and touch should be simultaneous. If one of the signals arrives with a delay, the brain adjusts its expectations to make it seem as though both events happened closer in time.

And that is the inspiration for this toy project: to replicate the effect claimed by the author.

## Implementation
As I had recently completed [Princeton’s Algs4 online course](https://algs4.cs.princeton.edu/home/), I was familiar with [algs4.jar](https://algs4.cs.princeton.edu/code/) and associated libraries like [StdDraw](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdDraw.html) and [StdRandom](https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdRandom.html). Using these libraries, I created a button that behaved in the manner described above.

Specifically, the user would be presented with a big red button and an instruction to press the button. 
![Image of red button](./red%20button.png)

The user can press the button in two ways: (1) clicking the button using the cursor; or (2) pressing the space bar on the keyboard. Upon pressing the button, the button will turn green.
![Image of green button](./green%20button.png)

Normally, the button will turn green after a delay of `BEFORE_PAUSE` (in milliseconds). (Eagleman was pretty non-committal about his specification of this delay “a tenth of a second”, so by making it a variable, it could be tweaked easily.) The green button will stay green for a duration of `AFTER_PAUSE` (in milliseconds), before reverting to red.

On a “random” button press (to be specified in greater detail later), however, the red button will turn green *immediately*, without the `BEFORE_PAUSE`. If the excerpt is right, the user should experience reversed causality, i.e. the button turned green before he tapped it. 

That’s pretty much it. 

A point worth elaborating is how I designed the occurrence of the delay-less green flash.

First, the user has to be “trained” to expect the `BEFORE_PAUSE` delay. I wasn’t sure how many button presses are necessary for this training, so I used the `SAFE_NUM` integer variable to make sure that the delay-less green flash would not occur in the first `SAFE_NUM` button presses.

Second, a recurring theme in the book was that our brains are actually pretty good at subconsciously detecting patterns that are inaccessible to our conscious mind. (Hence the title “Incognito”.) If the delay-less flash always occur at the *k*-th button press, the user’s subconscious mind might detect this and thus “anticipate it”, thus reducing the perception of reversed causality. To work around this, in each button press after the first `SAFE_NUM` button presses, there is a random chance that the delay-less green flash would occur. The likelihood is specified using `ODDS` (in %). 

I used these variables for modularity, so that it’s easy to experiment with different values to “optimize” the perception of reversed causality. The values found in the code below are merely the last set of values I experimented with—I am not sure that they’re optimal!

## Result
As a user, I could experience the reversed causality described by the author. However, it is pretty subtle. Perhaps the effect would be more pronounced on someone who did not design the behavior of the button. You are welcome to try it out yourself!

## Interdependencies
Besides the built-in Java libraries, I used the Princeton java libraries, [StdDraw](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/StdDraw.java.html) and [StdRandom](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/StdRandom.java.html). 

## Legal
The Princeton Java library algs4.jar is released under the [GNU General Public License, version 3 (GPLv3)](https://www.gnu.org/licenses/gpl-3.0.html). As such, this code is governed by the same. 
