import type {SidebarsConfig} from '@docusaurus/plugin-content-docs';

const sidebars: SidebarsConfig = {
  docsSidebar: [
    'getting-started',
    'theme',
    {
      type: 'category',
      label: 'Components',
      collapsed: false,
      items: [
        'components/status-badge',
        'components/card',
        'components/list-item',
        'components/text-field',
        'components/select',
        'components/checkbox',
        'components/button',
        'components/filter-chip',
        'components/search-bar',
        'components/section-header',
        'components/avatar',
        'components/icon-box',
      ],
    },
    {
      type: 'category',
      label: 'Layout',
      collapsed: false,
      items: [
        'layout/scaffold',
        'layout/navbar',
        'layout/bottom-bar',
        'layout/tab-bar',
        'layout/page-header',
        'layout/sidebar',
      ],
    },
  ],
};

export default sidebars;
