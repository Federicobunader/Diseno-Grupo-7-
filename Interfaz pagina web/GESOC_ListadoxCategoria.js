// Initiate new vue instance
var app = new Vue({
  // Attach Vue Instance to our div via id
  el: '#app',

  // Setup data to be used in our app
  data: {
    /**
            * Item List
            * This can be created manually, as it is here,
            * or it can also be passed as a prop from a CMS,
            * or you can retrieve it from an API or external source
            * @type {Array}
          */
    items: [
    {
      id: 0,
      title: 'Sample Title 1',
      categories: [
      {
        'category_name': "News",
        'category_url_title': "news" }],


      date: '1583941830' },

    {
      id: 1,
      title: 'Post Example 2',
      categories: [
      {
        'category_name': "Blog",
        'category_url_title': "blog" }],


      date: '935107200' },

    {
      id: 2,
      title: 'Feature 2319',
      categories: [
      {
        'category_name': "Feature",
        'category_url_title': "feature" }],


      date: '1233941830' },

    {
      id: 3,
      title: 'Produit 78',
      categories: [
      {
        'category_name': "Produits",
        'category_url_title': "produits" }],


      date: '1483941830' },

    {
      id: 4,
      title: 'Yababababa',
      categories: [
      {
        'category_name': "Nonsense",
        'category_url_title': "nonsense" }],


      date: '1383941830' }],


    /**
                               * This will contain our filtered items
                               * @type {Array}
                             */
    filteredList: [],
    /**
                        * Bound to our search input
                        * @type {String}
                      */
    keywords: '',
    /**
                    * Will be generated from the passed in items
                    * @type {Array}
                  */
    categories: [],

    /**
                      * The selected category - bound to the category select
                      * @type {String}
                    */
    category: 'all' },


  // Watch specific data for changes, if data mutates, activate code within.
  watch: {
    // we want to watch the category and keywords variables
    // and call `filterAll()` on either of them changing
    // this will ensure both filters are applied

    // When keywords is updated/changed in data, this function will be called
    keywords() {
      this.filterAll();
    },

    // When the category in data is updated/changed, this function will be called
    category() {
      this.filterAll();
    } },


  // Once app is initially created, run this code
  created() {

  },

  // Once app is mounted on our app div, run this code
  mounted() {
    // Reset app on mounted
    this.reset();

    // Setup categories list based on the items we have access to
    this.categories = this.getCategories();
  },

  // Store all of our methods here
  methods: {
    /**
               * Filter the list from the free text search
               * Nothing too fancy, but it works
               * @return {void}
             */
    filterByKeywords() {
      if (!this.keywords) {
        return;
      }

      this.filteredList = this.filteredList.filter(item => {
        // bringing everything to lowercase to make comparisons more reliable
        // i.e., "Search" !== "search"
        // let itemName = item.title.toLowerCase();
        // let searchQuery = this.keywords.toLowerCase();

        // We can also be a little more strict and strip additional user input
        let itemName = item.title.toLowerCase().
        replace(/[^a-z0-9\s]/gi, '').
        replace(/s\b/gi, '');
        let searchQuery = this.keywords.toLowerCase().
        replace(/[^a-z0-9\s]/gi, '').
        replace(/s\b/g, '');

        return itemName.indexOf(searchQuery) !== -1;
      });
    },

    /**
         * Go through all supplied items and get the categories
         * @return {array}
       */
    getCategories() {
      let categories = this.items.map(item => {
        return item.categories;
      });

      // horrible name, horrible, but we need to flatten those categories
      // Haha flat kitties, only kidding, we love cats here.
      let flatCats = [].concat.apply([], categories);

      console.log(flatCats);

      // Generate unique objects
      flatCats = flatCats.filter((obj, pos, arr) => {
        return flatCats.map(mapObj => mapObj['category_url_title']).indexOf(obj['category_url_title']) === pos;
      });

      return flatCats.sort(function (a, b) {
        return a.category_name > b.category_name ? 1 : -1;
      });
    },

    /**
         * Filter the list by category
         * @return {void}
       */
    filterByCategory() {
      // If there's no category exit function
      if (!this.category) {
        return;
      }

      if (this.category !== 'all') {
        // We filter the filteredList here using the filter function
        // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/filter
        this.filteredList = this.filteredList.filter(entry => {

          // we use index of to determine whether our entry contains this category
          //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/indexOf
          // index of returns -1 if the value isn't found
          return entry.categories.map(cat => cat.category_url_title).indexOf(this.category) !== -1;
        });
      }
    },

    /**
         * Filter everything
         * @return {void}
         */
    filterAll() {
      // Reset the filtered list to all items first.
      // We don't want to keep filtering the same list or we'll be left with no items.
      this.filteredList = this.items;

      // Filter by Keywords
      this.filterByKeywords();

      // Filter by Category
      this.filterByCategory();
    },

    // Reset App
    reset() {
      // Typically you don't want to litter your vue app with console logs
      // During development, you'll use the vue devtools extension
      console.log("Reset App");

      // Reset filtered list to the initial items list
      this.filteredList = this.items;

      // Reset keywords filter
      this.keywords = "";

      // Reset category filter
      this.category = 'all';
    } } });


    $('.tab a').on('click', function (e) {
  
      e.preventDefault();
      
      $(this).parent().addClass('active');
      $(this).parent().siblings().removeClass('active');
      
      target = $(this).attr('href');
    
      $('.tab-content > div').not(target).hide();
      
      $(target).fadeIn(600);
      
    });
    
    $(function() {
      $('a.page-scroll').bind('click', function(event) {
          var $anchor = $(this);
          $('html, body').stop().animate({
              scrollTop: $($anchor.attr('href')).offset().top
          }, 1500, 'easeInOutExpo');
          event.preventDefault();
      });
    });