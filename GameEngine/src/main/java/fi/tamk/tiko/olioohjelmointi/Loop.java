package fi.tamk.tiko.olioohjelmointi;

/**
 * Defines the game loop.
 * 
 * @author  Joni Alanko <joni.alanko@cs.tamk.fi>
 * @since   2018.1812
 * @version 1.0
 */
public interface Loop {

    /**
     * Renders the game.
     *
     * @param deltaTime indicates how many seconds passed from last render() call.
     */
    public void render(float deltaTime);
}