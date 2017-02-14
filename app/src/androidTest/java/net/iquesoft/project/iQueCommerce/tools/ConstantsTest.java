package net.iquesoft.project.iQueCommerce.tools;

public class ConstantsTest {

    public static final String SHOP_NAME = "Shop name";

    public static final String FIRST_NAME = "Vasiliy";

    public static final String LAST_NAME = "Pupkin";

    public static final String BAD_EMAIL = "bad/type_email*,@gmail.com";

    public static final String EMAIL = "seed.iquesoft@gmail.com";

    public static final String BAD_PASSWORD = "Bad*type,password";

    public static final String PASSWORD = "somePassword";

    public static final String PHONE_NUMBER = "380123456789";

    public static final String SHIPPING_ADDRESS = "Curve St., 21";

    public static final String SHIPPING_CITY = "Bearmington";

    public static final String SHIPPING_POSTAL = "15324";

    public static final String SHIPPING_COUNTRY = "Australia";

    public static final String BILLING_ADDRESS = "Straight Avenue, 12";

    public static final String BILLING_CITY = "Vinetown";

    public static final String BILLING_POSTAL = "65474";

    public static final String BILLING_COUNTRY = "Austria";

    public static final long CUSTOMER_ID = 123456789L;

    public static final long CUSTOMER_ORDERS_COUNT = 10L;

    public static final String CUSTOMER_TOTAL_SPENT = "20";

    public static final String DISCOUNT_CODE = "DA56168431638";

    public static final String NAVIGATION_MENU_LOG_OUT = "Log out";

    public static final String NAVIGATION_MENU_WISH_LIST = "Favourites";

    public static final String NAVIGATION_MENU_ACCOUNT_SETTINGS = "Account settings";

    public static final String CUSTOMER_INFO = "Full name: " + FIRST_NAME + " " + LAST_NAME + "\n"
            + "Phone: " + PHONE_NUMBER + "\n"
            + "Email: " + EMAIL;

    public static final String SHIPPING_ADDRESS_INFO = "Address: " + SHIPPING_ADDRESS + "\n"
            + "City and province: " + SHIPPING_CITY + ", " + "null" + "\n"
            + "Zip and country: " + SHIPPING_POSTAL + ", " + "null";

    public static final String BILLING_ADDRESS_INFO = "Address: " + BILLING_ADDRESS + "\n"
            + "City and province: " + BILLING_CITY + ", " + "null" + "\n"
            + "Zip and country: " + BILLING_POSTAL + ", " + "null";

    public static final String[] COLLECTION_TITLES = {"Notebooks", "Software", "Jewelry", "Digital downloads",
            "Accessories", "Books", "Others", "Clothing", "Cell phones", "Camera-photo", "Shoes" };

    public static final String[] COLLECTION_IMAGES = {"https://cdn.shopify.com/s/files/1/1442/1018/collections/0000016_gift-cards.jpeg?v=1471588148",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000002_desktops.jpg?v=1471587658",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000010_shoes.jpeg?v=1471587979",
            " https://cdn.shopify.com/s/files/1/1442/1018/collections/0000011_clothing.jpeg?v=1471588004",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000012_apparel-accessories.jpg?v=1471588054",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000003_notebooks.jpg?v=1471587700",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000013_digital-downloads.jpeg?v=1471588077",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000004_software.jpg?v=1471587728",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000006_camera-photo.jpeg?v=1471587789",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000007_cell-phones.jpeg?v=1471587816",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000008_accessories.jpg?v=1471587912",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000015_jewelry.jpeg?v=1471588121",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000014_book.jpeg?v=1471588098",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000009_apparel.jpeg?v=1471587958",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000005_electronics.jpeg?v=1471587757",
            "https://cdn.shopify.com/s/files/1/1442/1018/collections/0000001_computers.jpeg?v=1471587507" };

    public static final String[] NOTEBOOK_TITLE = {"Apple MacBook Pro 13-inch", "Asus N551JK-XO076H Laptop", "HP Envy 6-1180ca 15.6-Inch Sleekbook",
            "HP Spectre XT Pro UltraBook", "Lenovo Thinkpad X1 Carbon Laptop", "Samsung Series 9 NP900X4C Premium Ultrabook" };

    public static final String[] NOTEBOOK_PRICES = {"1800.00", "1500.00", "1460.00", "1350.00", "1360.00", "1590.00"};

    public static final String[] NOTEBOOK_DESCRIPTIONS = {"<p>With fifth-generation Intel Core processors, the latest graphics, and faster flash storage, the incredibly advanced MacBook Pro with Retina display moves even further ahead in performance and battery life.* *Compared with the previous generation.</p><p>Retina display with 2560-by-1600 resolution</p><p>Fifth-generation dual-core Intel Core i5 processor</p><p>Intel Iris Graphics</p><p>Up to 9 hours of battery life1</p><p>Faster flash storage2</p><p>802.11ac Wi-Fi</p><p>Two Thunderbolt 2 ports for connecting high-performance devices and transferring data at lightning speed</p><p>Two USB 3 ports (compatible with USB 2 devices) and HDMI</p><p>FaceTime HD camera</p><p>Pages, Numbers, Keynote, iPhoto, iMovie, GarageBand included</p><p>OS X, the world's most advanced desktop operating system</p>",
            "<p>The ASUS N550JX combines cutting-edge audio and visual technology to deliver an unsurpassed multimedia experience. A full HD wide-view IPS panel is tailor-made for watching movies and the intuitive touchscreen makes for easy, seamless navigation. ASUS has paired the N550JXвЂ™s impressive display with SonicMaster Premium, co-developed with Bang &amp; Olufsen ICEpowerВ® audio experts, for true surround sound. A quad-speaker array and external subwoofer combine for distinct vocals and a low bass that you can feel.</p>",
            "The UltrabookTM that's up for anything. Thin and light, the HP ENVY is the large screen UltrabookTM with Beats AudioTM. With a soft-touch base that makes it easy to grab and go, it's a laptop that's up for anything.<br><br><b>Features</b><br><br>- Windows 8 or other operating systems available<br><br><b>Top performance. Stylish design. Take notice.</b><br><br>- At just 19.8 mm (0.78 in) thin, the HP ENVY UltrabookTM is slim and light enough to take anywhere. It's the laptop that gets you noticed with the power to get it done.<br>- With an eye-catching metal design, it's a laptop that you want to carry with you. The soft-touch, slip-resistant base gives you the confidence to carry it with ease.<br><br><b>More entertaining. More gaming. More fun.</b><br><br>- Own the UltrabookTM with Beats AudioTM, dual speakers, a subwoofer, and an awesome display. Your music, movies and photo slideshows will always look and sound their best.<br>- Tons of video memory let you experience incredible gaming and multimedia without slowing down. Create and edit videos in a flash. And enjoy more of what you love to the fullest.<br>- The HP ENVY UltrabookTM is loaded with the ports you'd expect on a world-class laptop, but on a Sleekbook instead. Like HDMI, USB, RJ-45, and a headphone jack. You get all the right connections without compromising size.<br><br><b>Only from HP.</b><br><br>- Life heats up. That's why there's HP CoolSense technology, which automatically adjusts your notebook's temperature based on usage and conditions. It stays cool. You stay comfortable.<br>- With HP ProtectSmart, your notebook's data stays safe from accidental bumps and bruises. It senses motion and plans ahead, stopping your hard drive and protecting your entire digital life.<br>- Keep playing even in dimly lit rooms or on red eye flights. The optional backlit keyboard[1] is full-size so you don't compromise comfort. Backlit keyboard. Another bright idea.<br><br><b></b>",
            "<p>Introducing HP ENVY Spectre XT, the Ultrabook designed for those who want style without sacrificing substance. It's sleek. It's thin. And with Intel. Corer i5 processor and premium materials, it's designed to go anywhere from the bistro to the boardroom, it's unlike anything you've ever seen from HP.</p>",
            "<p>The X1 Carbon brings a new level of quality to the ThinkPad legacy of high standards and innovation. It starts with the durable, carbon fiber-reinforced roll cage, making for the best Ultrabook construction available, and adds a host of other new features on top of the old favorites. Because for 20 years, we haven't stopped innovating. And you shouldn't stop benefiting from that.</p>",
            "<p>Designed with mobility in mind, Samsung's durable, ultra premium, lightweight Series 9 laptop (model NP900X4C-A01US) offers mobile professionals and power users a sophisticated laptop equally suited for work and entertainment. Featuring a minimalist look that is both simple and sophisticated, its polished aluminum uni-body design offers an iconic look and feel that pushes the envelope with an edge just 0.58 inches thin. This Series 9 laptop also includes a brilliant 15-inch SuperBright Plus display with HD+ technology, 128 GB Solid State Drive (SSD), 8 GB of system memory, and up to 10 hours of battery life.</p>" };

    public static final String[] NOTEBOOK_IMAGES = {"https://cdn.shopify.com/s/files/1/1442/1018/products/0000025_apple-macbook-pro-13-inch.jpeg?v=1471626294",
            "https://cdn.shopify.com/s/files/1/1442/1018/products/0000026_asus-n551jk-xo076h-laptop.jpeg?v=1471626335",
            "https://cdn.shopify.com/s/files/1/1442/1018/products/0000030_hp-envy-6-1180ca-156-inch-sleekbook.jpeg?v=1471626572,",
            "https://cdn.shopify.com/s/files/1/1442/1018/products/0000028_hp-spectre-xt-pro-ultrabook.jpeg?v=1471626599",
            "https://cdn.shopify.com/s/files/1/1442/1018/products/0000031_lenovo-thinkpad-x1-carbon-laptop.jpeg?v=1471626798",
            " https://cdn.shopify.com/s/files/1/1442/1018/products/0000027_samsung-series-9-np900x4c-premium-ultrabook.jpeg?v=1471627509" };
}

